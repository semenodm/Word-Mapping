package org.education

import static org.junit.Assert.*

import org.apache.http.message.BasicHttpResponse;
import org.junit.Test;
import org.scribe.builder.api.Api;
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

import groovyx.net.http.HTTPBuilder

class OAuthCourseHeroHttpBuilderTest {

	@Test
	void testOAuth(){
		String apiKey = "qEzfZGh59R"
		String apiSecret = "eHBbq4kdAJD5DBVPuYQGYEkfp23URTxe"

		def http =  new HTTPBuilder("https://quizlet.com")
		//http.auth.oauth(apiKey, apiSecret, null, null)
		
		http.get(path : '/authorize/', query : [ response_type:'code', client_id:'qEzfZGh59R', scope:'read', state:'RANDOM_STRING']){resp, html ->

			//println header
			//			resp.headers.each {
			//				println "  ${it.name} : ${it.value}"
			//				assert it.name && it.value
			//			}
			println html
		}
	}
	@Test
	public void testHeadMethod() {
		def http = new HTTPBuilder('http://www.google.com')

		def html = http.get( path : '/search', query : [q:'Groovy'] )

		assert html instanceof groovy.util.slurpersupport.GPathResult
		assert html.HEAD.size() == 1
		assert html.BODY.size() == 1
	}
}
