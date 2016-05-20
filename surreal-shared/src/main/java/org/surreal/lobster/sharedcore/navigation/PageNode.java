package org.surreal.lobster.sharedcore.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PageNode implements Serializable {
	/** Generated serial version id */
	private static final long serialVersionUID = 5235376928559380532L;
	/**
	 * List of children page nodes
	 */
	private final List<PageNode> children = new ArrayList<PageNode>();
	/**
	 * The page of this page node
	 */
	private final Page page;
	/**
	 * The parent of this page node
	 */
	private PageNode parent;
	/**
	 * Any extra info we need to know about this page node.
	 * usually a guid.
	 */
	private final String xInfo;
	
	public PageNode() {
		this(null, null);
	}
	
	public PageNode(Page page) {
		this(page, null);
	}
	
	public PageNode(Page page, String xInfo) {
		this.page = page;
		this.xInfo = xInfo;
	}
	
	/**
	 * @return the children
	 */
	public final List<PageNode> getChildren() {
		return children;
	}

	/**
	 * @return the page
	 */
	public final Page getPage() {
		return page;
	}

	/**
	 * @return the xInfo
	 */
	public final String getxInfo() {
		return xInfo;
	}

	/**
	 * Adds the given page node as a child of this parent
	 * @param child to be added
	 * @return the index of the given child in the children list
	 */
	public int addChild(PageNode child) {
		if (child == this) {
			throw new IllegalArgumentException("child cannot be the same instance as the parent");
		}
		child.parent = this;
		if (children.indexOf(child) < 0) { // && !equals(pageNode)) {
			children.add(child);
		}
		return children.indexOf(child);
	}
	
	/**
	 * Removes the given child from the list of children 
	 * @param pageNode to be removed
	 */
	public void removeChild(PageNode pageNode) {
		children.remove(pageNode);
	}

	/**
	 * Determines if this page node has children
	 * @return true if this page node has children
	 */
	public Boolean hasChildren() {
		return !children.isEmpty();
	}
	
	/**
	 * Convenience method when we don't want to specify the caller
	 * @return the next page node
	 */
	public PageNode getNext() {
		return getNext(null);
	}
	
	/**
	 * Convenience method when we don't want to specify the caller
	 * @return the previous page node
	 */
	public PageNode getPrevious() {
		return getPrevious(null);
	}
	
	/**
	 *  Tries to return the next node in the tree that has page.
	 *  If it can't find a valid node it should return null.
	 * @see the visio flow chart (pegaus web navigation logic.vsd page 1)
	 * @param childCaller that's asking for the next node.
	 * @return the next page node or null if a valid page node can't be found.
	 */
	private PageNode getNext(PageNode childCaller) {
		PageNode nextPageNode = null;
		if (hasChildren()) {
			boolean externalCaller = childCaller == null;
			if (externalCaller) {
				PageNode firstChild = children.get(0);
				if (firstChild.getPage() == null) {
					nextPageNode = firstChild.getNext();
				} else {
					nextPageNode = firstChild;
				}
			} else {
				int childIdx = children.indexOf(childCaller);
				boolean lastChild = childIdx >= children.size()-1;
				if (lastChild) {
					if (parent != null) {
						nextPageNode = parent.getNext(this);
					}
				} else {
					PageNode nextChild = children.get(childIdx+1);
					if (nextChild.getPage() == null) {
						nextPageNode = nextChild.getNext();
					} else {
						nextPageNode = nextChild;
					}
				}
			}
		} else {
			if (parent != null) {
				nextPageNode = parent.getNext(this);
			}
		}
		return nextPageNode;
	}
	
	/**
	 * Tries to return the previous node in the tree that has a page.
	 * If it can't find a valid node it should return null.
	 * @see the visio flow chart (pegaus web navigation logic.vsd page 2)
	 * @param caller that's asking for the previous node.
	 * @return the previous page node or null if a valid page node can't be found.
	 */
	private PageNode getPrevious(PageNode caller) {
		PageNode previousPageNode = null;
		if (caller == parent && caller!= null) {
			PageNode lastChild = children.get(children.size()-1);
			if (lastChild.hasChildren()) {
				previousPageNode = lastChild.getPrevious(this);
			} else {
				previousPageNode = lastChild;
			}
		} else {
			if (children.contains(caller)) {
				int callerIndex = children.indexOf(caller);
				if (callerIndex > 0) {
					PageNode previousChild = children.get(callerIndex-1);
					if (previousChild.hasChildren()) {
						previousPageNode = previousChild.getPrevious(this);
					} else {
						previousPageNode = previousChild;
					}
				} else {
					if (this.page == null) {
						if (this.parent != null) {
							previousPageNode = parent.getPrevious(this);
						}
					} else {
						previousPageNode = this;
					}
				}
			} else {
				if (this.parent != null) {
					previousPageNode = parent.getPrevious(this);
				}
			}
		}
		return previousPageNode;
	}

	/**
	 * There is an assumption here that a given page node will never 
	 * have a child page node with the same page field as the parent.
	 *  
	 * @param search
	 * @return
	 */
	public PageNode findAlterEgo(PageNode search) {
		PageNode alterEgo = null;
		if (search != null && search.getPage() == getPage()) {
			if (search.getxInfo() == null) {
				alterEgo = this;
			} else {
				if (search.getxInfo().equals(getxInfo())) {
					alterEgo = this;
				}
			}
		} else {
			for (PageNode child : children) {
				alterEgo = child.findAlterEgo(search);
				if (alterEgo != null) {
					break;
				}
			}
		}
		return alterEgo;
	}

	public boolean isOrphan() {
		return parent == null;
	}
	
}
