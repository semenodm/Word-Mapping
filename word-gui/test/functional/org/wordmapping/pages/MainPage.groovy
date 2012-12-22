package org.wordmapping.pages

import geb.Page
import groovy.lang.MetaClass;

class MainPage extends Page {
	
	static url = "person/list"
	
	static content = {
		dictionary_panel { $("a", text: "New Person") }
	}
}
