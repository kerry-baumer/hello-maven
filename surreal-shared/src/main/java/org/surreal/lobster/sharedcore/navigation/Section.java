package org.surreal.lobster.sharedcore.navigation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public enum Section {
	
	GENERAL_INFORMATION(0, "General Information", 
			Page.GENERAL_INFO, 
			Page.POC, 
			Page.ENVIRONMENT, 
			Page.AUTHORIZED_DRAFTERS, 
			Page.COI),
	INVOLVED_VESSEL(1, "Involved Vessel", 
			Page.INVOLVED_ENTITY),
	INVOLVED_PERSONNEL(2, "Involved Personnel", 
			Page.INVOLVED_PERSONNEL,
			Page.PPE,
			Page.CERTS,
			Page.INJURY),
	INVOLVED_PROPERTY(3, "Involved Property", 
			Page.INVOLVED_PROPERTY),
	FACTORS_RECOMMENDATIONS(4, "Factors Recommendations", 
			Page.FACTORS),
	QA(5, "Qa", 
			Page.QA),
	VALIDATION(6, "Validation", 
			Page.VALIDATION);
	
	public int index;
	private Set<Page> pages;
	private String titleCase;
	private static final Map<Page, Set<Section>> INVERSE_LOOKUP = new HashMap<Page, Set<Section>>();
	
	static {
		for (final Section section : Section.values()) {
			for (final Page page : section.pages) {
				if (INVERSE_LOOKUP.containsKey(page)) {
					INVERSE_LOOKUP.get(page).add(section);
				} else {
					final Set<Section> set = new HashSet<Section>();
					set.add(section);
					INVERSE_LOOKUP.put(page, set);
				}
			}
		}
	}
	
	private Section(int index, String titleCase, Page...pages) {
		this.index = index;
		this.titleCase = titleCase;
		this.pages = new HashSet<Page>(Arrays.asList(pages));
	}
	
	public Set<Page> getPages() {
		return pages;
	}
	
	public static Section findSectionFor(Page page) {
		Section section = null;
		if (page != null) {
			Set<Section> set = INVERSE_LOOKUP.get(page);
			if (set == null) {
				System.out.println("There is no section for page: " + page.toString());
			} else {
				for (Section s : set) {
					section = s;
					break;
				}
			}
		}
		return section;
	}

	public String getTitleCase() {
		return titleCase;
	}
	
}