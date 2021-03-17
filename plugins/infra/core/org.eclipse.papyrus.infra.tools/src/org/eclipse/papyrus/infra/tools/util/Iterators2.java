/*****************************************************************************
 * Copyright (c) 2014, 2020 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.tools.util;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.util.TreeIterator;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;

/**
 * Utilities for working with iterators that are not provided by {@linkplain Iterators Guava}.
 */
public class Iterators2 {
	/**
	 * Not instantiable by clients.
	 */
	private Iterators2() {
		super();
	}

	/**
	 * Filters an EMF tree iterator for elements of a particular {@code type}.
	 *
	 * @param treeIterator
	 *            the tree iterator to filter
	 * @param type
	 *            the type of elements to include in the filtered tree iterator
	 * @return the filtered tree iterator
	 */
	public static <T> TreeIterator<T> filter(final TreeIterator<?> treeIterator, final Class<? extends T> type) {
		class FilteredTreeIterator extends AbstractIterator<T> implements TreeIterator<T> {
			final Iterator<? extends T> delegate = Iterators.filter(treeIterator, type);

			@Override
			protected T computeNext() {
				return delegate.hasNext() ? delegate.next() : endOfData();
			}

			@Override
			public void prune() {
				treeIterator.prune();
			}
		}

		return new FilteredTreeIterator();
	}

	/**
	 * Obtain a spliterator over an EMF tree iterator. The spliterator will have characteristics implied by an
	 * EMF content tree, namely:
	 * <ul>
	 * <li>{@link Spliterator#ORDERED}</li>
	 * <li>{@link Spliterator#DISTINCT}</li>
	 * <li>{@link Spliterator#NONNULL}</li>
	 * </ul>
	 *
	 * @param <T>
	 *            the tree element type
	 * @param treeIterator
	 *            a tree iterator
	 * @return a spliterator over the tree
	 */
	public static <T> Spliterator<T> spliterator(final TreeIterator<T> treeIterator) {
		return Spliterators.spliteratorUnknownSize(treeIterator, Spliterator.ORDERED | Spliterator.DISTINCT | Spliterator.NONNULL);
	}

	/**
	 * Obtain a stream over an EMF tree iterator.
	 *
	 * @param <T>
	 *            the tree element type
	 * @param treeIterator
	 *            a tree iterator
	 * @return a stream over the tree
	 */
	public static <T> Stream<T> stream(final TreeIterator<T> treeIterator) {
		return StreamSupport.stream(spliterator(treeIterator), false);
	}

}
