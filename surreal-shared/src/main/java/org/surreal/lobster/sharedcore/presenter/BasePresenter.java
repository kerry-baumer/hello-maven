/**
 * Mar 15, 2012 3:53:00 PM
 */
package org.surreal.lobster.sharedcore.presenter;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sharedcore.databroker.CommonDecodeDataBroker;
import org.surreal.lobster.sharedcore.databroker.DataBrokerCallback;
import org.surreal.lobster.sharedcore.databroker.DataBrokerProcessingException;
import org.surreal.lobster.sharedcore.databroker.DecodeDataBroker;
import org.surreal.lobster.sharedcore.model.OptionItem;
import org.surreal.lobster.sharedcore.model.OptionItemDetailBean;
import org.surreal.lobster.sharedcore.view.HasItems;
import org.surreal.lobster.sharedcore.view.OptionListItem;
import org.surreal.lobster.sharedcore.view.OptionListItemDetail;

/**
 * Base support for presenters, providing popular data broker interaction
 * methods.
 * 
 * @author daniel.wilkin
 */
public class BasePresenter {

	/**
	 * A loader suitable for extracting decodes from a particular decode data
	 * broker instance.
	 * 
	 * @author daniel.wilkin
	 */
	protected class DecodeLoader {
		private final CommonDecodeDataBroker commonBroker;
		@SuppressWarnings("unused")
		private final DecodeDataBroker<?> decodeBroker;

		private DecodeLoader(CommonDecodeDataBroker broker) {
			this.commonBroker = broker;
			this.decodeBroker = null;
		}

		private DecodeLoader(DecodeDataBroker<?> decodeBroker) {
			this.commonBroker = null;
			this.decodeBroker = decodeBroker;
		}

		/**
		 * Return the list of decodes for a particular field from a
		 * {@link CommonDecodeDataBroker}. Set the selected flag if a report is
		 * loaded.
		 * 
		 * @param value
		 *            The code for the <code>OptionItem</code> instance
		 * @param group
		 *            The decode group for which the specified field is a member
		 * @param field
		 *            The field whose decodes are to be retrieved
		 * @return The decodes for the specified field
		 */
		public List<OptionListItem> forDecode(final String value, final DecodeGroup group, final DecodeField field) {
			if (commonBroker == null) {
				throw new IllegalStateException("Must have been initialized with a CommonDecodeDataBroker instance.");
			}
			final List<OptionListItem> wrappedDecodes = new ArrayList<OptionListItem>();
			commonBroker.loadDecodeData(group, new DataBrokerCallback<Void>() {
				@Override
				public void onFailure(Throwable t) {
					throw new DataBrokerProcessingException("Unable to load decode data for " + field, t);
				}

				@Override
				public void onSuccess(Void result) {
					final List<OptionItem> decodes = commonBroker.getDecodeData(field);
					for (final OptionItem optionItem : decodes) {
						boolean selected = false;
						if (value != null && optionItem.getValue().equals(value)) {
							selected = true;
						}
						wrappedDecodes.add(createListItem(optionItem, selected));
					}
				}
			});
			if (wrappedDecodes.isEmpty()) {
				throw new DataBrokerProcessingException("Data for group " + group + " may not have been loaded yet.  "
						+ "Refer to the TwoPhaseDecodeDataBroker contract for details.");
			}
			return wrappedDecodes;
		}

		/**
		 * Return the list of decodes with embedded current selection information.
		 * @param values The list of previously user selected decodes
		 * @param group The decode group associated with this request 
		 * @param field The specific decode field being retrieved
		 * @return The list of displayable decodes and their individual selected status
		 */
		public List<OptionListItemDetail> forDecode(final List<OptionItem> values, DecodeGroup group,
				final DecodeField field) {
			if (commonBroker == null) {
				throw new IllegalStateException("Must have been initialized with a CommonDecodeDataBroker instance.");
			}
			final List<OptionListItemDetail> wrappedDecodes = new ArrayList<OptionListItemDetail>();
			commonBroker.loadDecodeData(group, new DataBrokerCallback<Void>() {
				@Override
				public void onFailure(Throwable t) {
					throw new DataBrokerProcessingException("Unable to load decode data for " + field, t);
				}

				@Override
				public void onSuccess(Void result) {
					// TODO: performance: this method runs O(n*p), but should be changed to O(p+(n*log(p))) by using a Map for the decodes
					final List<OptionItem> decodes = commonBroker.getDecodeData(field);
					for (final OptionItem optionItem : decodes) {
						boolean selected = false;
						if (values != null) {
							for (OptionItem value : values) {
								if (value != null && optionItem.getValue().equals(value.getValue())) {
									selected = true;
								}
							}
						}
						wrappedDecodes.add(createDetailListItem(optionItem, selected));
					}
				}
			});
			if (wrappedDecodes.isEmpty()) {
				throw new DataBrokerProcessingException("Data for group " + group + " may not have been loaded yet.  "
						+ "Refer to the TwoPhaseDecodeDataBroker contract for details.");
			}
			return wrappedDecodes;
		}
	}

	/**
	 * Loads decode data from the specified broker instance.
	 * 
	 * @param commonBroker
	 *            The broker to load decodes from
	 * @return A loader
	 */
	protected DecodeLoader loadDecodesFrom(CommonDecodeDataBroker commonBroker) {
		return new DecodeLoader(commonBroker);
	}

	/**
	 * Loads decode data from the specified broker instance.
	 * 
	 * @param broker
	 *            The broker to load decodes from
	 * @return A loader
	 */
	protected DecodeLoader loadDecodesFrom(DecodeDataBroker<?> broker) {
		return new DecodeLoader(broker);
	}

	/**
	 * Obtains the selected of the specified displayable item.
	 * 
	 * @param optionListItem
	 *            The item
	 * @return
	 */
	protected String getSelectedValueFrom(OptionListItem optionListItem) {
		return (optionListItem == null) ? null : optionListItem.getItem().getValue();
	}

	/**
	 * Creates a displayable {@link OptionListItem} that defaults to a selected
	 * status of <code>true</code>.
	 * 
	 * @param item
	 *            The item to make displayable
	 * @return The displayable or wrapped decode data
	 */
	protected OptionListItem createListItem(final OptionItem item) {
		return createListItem(item, true);
	}

	/**
	 * Create a displayable {@link OptionListItem} with the selected flag set as
	 * specified.
	 * 
	 * @param value
	 *            The decode data instance
	 * @param selected
	 *            The data's selected status
	 * @return The displayable or wrapped decode data
	 */
	protected OptionListItem createListItem(final OptionItem value, boolean selected) {
		final OptionListItem item = new OptionListItem(value);
		item.setSelected(selected);
		return item;
	}

	/**
	 * Creates a displayable {@link OptionListItemDetail} with the selected flag set
	 * as specified.
	 * @param value The decode data instance
	 * @param selected The data's selected status
	 * @return The displayable or wrapped detail decode data
	 */
	protected OptionListItemDetail createDetailListItem(final OptionItem value, boolean selected) {
		OptionListItemDetail item = new OptionListItemDetail(new OptionItemDetailBean(value));
		item.setSelected(selected);
		return item;
	}

	/**
	 * Retrieves the value or <code>code</code> in the specified wrapper instance. 
	 * @param item The wrapper whose value should be obtained
	 * @return The value or null if the item has no data
	 */
	protected String extractDecodeValue(HasItems<? extends OptionListItem> item) {
		return item.getData() == null || item.getData().isEmpty() ? null : item.getData().get(0).getValue();
	}
}
