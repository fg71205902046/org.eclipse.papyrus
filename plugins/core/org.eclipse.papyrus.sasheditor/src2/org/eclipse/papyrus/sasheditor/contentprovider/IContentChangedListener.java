/**
 * 
 */
package org.eclipse.papyrus.sasheditor.contentprovider;

/**
 * Interface to be implemented by classes needing to be notified when the SashContent model has
 * changed. This interface is not used by the sashesWindows itself. It exists to allows
 * ContentProvider implementation to provide a common notification mechanism.
 * 
 * @author dumoulin
 */
public interface IContentChangedListener {

	/**
	 * Event indicating the change.
	 * 
	 * @author dumoulin
	 */
	public class ContentEvent {

		public static final int ADDED = 0;

		public static final int REMOVED = 1;

		public static final int CHANGED = 2;

		public static final int MOVED = 3;

		/** type of event */
		private final int type;

		/** model to which the event apply */
		private final Object model;

		/** object added or removed or modified */
		private final Object object;

		/**
		 * @param type
		 * @param model
		 * @param object
		 */
		public ContentEvent(int type, Object model, Object object) {
			this.type = type;
			this.model = model;
			this.object = object;
		}

		/**
		 * @return the type
		 */
		// @unused
		public int getType() {
			return type;
		}

		/**
		 * @return the model
		 */
		// @unused
		public Object getModel() {
			return model;
		}

		/**
		 * @return the object
		 */
		// @unused
		public Object getObject() {
			return object;
		}

	}

	/**
	 * Method called when the content has changed.
	 */
	public void contentChanged(ContentEvent event);
}
