package org.wordmapping

import static org.hamcrest.CoreMatchers.*
import geb.spock.GebSpec

class InitialDictionarySpec extends GebSpec{

	def "initial dict is empty for new user"(){
		when:
		to MainPage
		then:
		dictionary_panel is empty		
	}
	
	def "create initial dictionary"(){
		when: "visit main page"
		to MainPage
		and: "upload my favourite book"
		then: "initial dictionary is created"
		dictionary_panel is notEmpty
	}
}
