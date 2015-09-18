/*****************************************************************************
 * Copyright (c) 2015 Christian W. Damus and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.aof.sync;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import org.eclipse.papyrus.aof.core.IObserver;
import org.eclipse.papyrus.aof.core.IOne;
import org.eclipse.papyrus.aof.core.impl.utils.cache.IBinaryCache;
import org.eclipse.papyrus.aof.core.impl.utils.cache.IUnaryCache;
import org.eclipse.papyrus.aof.core.impl.utils.cache.WeakKeysWeakValuesBinaryCache;
import org.eclipse.papyrus.aof.core.impl.utils.cache.WeakKeysWeakValuesUnaryCache;
import org.eclipse.papyrus.aof.core.utils.ObserverTracker;
import org.eclipse.papyrus.aof.core.utils.Observers;
import org.eclipse.papyrus.aof.sync.IMapping.Instance;

/**
 * Default implementation of the mapping context protocol.
 */
public class MappingContext implements IMappingContext {

	private final AtomicInteger openDepth = new AtomicInteger();

	private final IBinaryCache<IOne<?>, IMapping<?, ?>, IMapping.Instance<?, ?>> mappingInstances = new WeakKeysWeakValuesBinaryCache<>();
	private final ThreadLocal<IMapping.Instance<?, ?>> currentMappingInstance = new ThreadLocal<>();

	private final IUnaryCache<IObserver<?>, IObserver<?>> observerWrappers = new WeakKeysWeakValuesUnaryCache<>();

	public MappingContext() {
		super();
	}

	@Override
	public void open() {
		openDepth.incrementAndGet();

		IMapping.Instance<?, ?> context = currentMappingInstance.get();
		Observers.pushObserverIntercept((IObserver<?> o) -> (IObserver<?>) wrapObserver(o, context));
	}

	@Override
	public boolean isOpen() {
		return openDepth.get() > 0;
	}

	@Override
	public void close() {
		if (openDepth.decrementAndGet() < 0) {
			// Undo
			openDepth.incrementAndGet();
			throw new IllegalStateException("already closed"); //$NON-NLS-1$
		}

		Observers.popObserverIntercept();
	}

	@Override
	public <F, T> ObserverTracker run(Instance<F, T> instance, BiConsumer<? super IOne<F>, ? super IOne<T>> block) {
		ObserverTracker result;

		// Remember this instance for later ad hoc consequents
		mappingInstances.put(instance.getLeft(), instance.getType(), instance);

		IMapping.Instance<?, ?> parent = currentMappingInstance.get();
		if (parent != null) {
			parent.addConsequent(instance);
		} else {
			currentMappingInstance.set(instance);
		}

		try {
			result = run(instance.getLeft(), instance.getRight(), block);
		} finally {
			if (parent == null) {
				currentMappingInstance.remove();
			}
		}

		return result;
	}

	private <E> IObserver<? super E> wrapObserver(IObserver<E> observer, IMapping.Instance<?, ?> context) {
		@SuppressWarnings("unchecked")
		IObserver<? super E> result = (IObserver<? super E>) observerWrappers.get(observer);

		if (result == null) {
			result = new ContextualObserver<E>(observer, context);
			observerWrappers.put(observer, result);
		}

		return result;
	}
	//
	// Nested types
	//

	private class ContextualObserver<E> implements IObserver<E> {
		private final IObserver<E> delegate;
		private final IMapping.Instance<?, ?> context;

		ContextualObserver(IObserver<E> observer, IMapping.Instance<?, ?> context) {
			super();

			this.delegate = observer;
			this.context = context;
		}

		@Override
		public boolean isDisabled() {
			return delegate.isDisabled();
		}

		@Override
		public void setDisabled(boolean disabled) {
			delegate.setDisabled(disabled);
		}

		@Override
		public void added(int index, E element) {
			IMapping.Instance<?, ?> restore = pushContext();
			try {
				delegate.added(index, element);
			} finally {
				popContext(restore);
			}
		}

		@Override
		public void removed(int index, E element) {
			IMapping.Instance<?, ?> restore = pushContext();
			try {
				delegate.removed(index, element);
			} finally {
				popContext(restore);
			}
		}

		@Override
		public void replaced(int index, E newElement, E oldElement) {
			IMapping.Instance<?, ?> restore = pushContext();
			try {
				delegate.replaced(index, newElement, oldElement);
			} finally {
				popContext(restore);
			}
		}

		@Override
		public void moved(int newIndex, int oldIndex, E element) {
			IMapping.Instance<?, ?> restore = pushContext();
			try {
				delegate.moved(newIndex, oldIndex, element);
			} finally {
				popContext(restore);
			}
		}

		private IMapping.Instance<?, ?> pushContext() {
			IMapping.Instance<?, ?> result = currentMappingInstance.get();
			currentMappingInstance.set(context);
			return result;
		}

		private void popContext(IMapping.Instance<?, ?> restore) {
			currentMappingInstance.set(restore);
		}
	}
}
