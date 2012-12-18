package org.education.fileanalizer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.megadict.format.dict.DICTDictionary;
import com.megadict.format.dict.index.IndexFile;
import com.megadict.format.dict.reader.DictionaryFile;
import com.megadict.model.Definition;

/**
 * Unit test for simple App.
 */
public class FileAnalizerTest {

	private CardinalityCalculator calculator;

	@Before
	public void setUp() {
		calculator = new DefaultCardinalityCalculator();
	}

	@Test
	public void verify_We_ll_Word() {
		// Given a text
		String text = "We'll  \"rock you\" accursed.” “they’ll";

		for (String s : text.split("\\W++")) {
		//for (String s : text.split("(\\b[\\s]+\\b)")) {
			System.out.println(s);
			
		}
		
		// when
		Set<Pair> cardinality = calculator.extractCardinality(text);

		// then
		assertThat(cardinality.contains(new Pair("we'll", 1L)), is(Boolean.TRUE));
	}

	@Test
	public void verifyCardinalityCalculatorWorkProperly() {
		// Given a text
		String text = "No one can tell me nobody knows, \"where the wind\" comes from, where the wind goes";

		// when
		Set<Pair> cardinality = calculator.extractCardinality(text);

		// then
		assertThat("Size mismatch", cardinality.size(), equalTo(14));
		assertThat(cardinality.contains(new Pair("wind", 2L)), is(Boolean.TRUE));
		assertThat(cardinality.contains(new Pair("where", 2L)),
				is(Boolean.TRUE));
		assertThat(cardinality.contains(new Pair("knows", 1L)),
				is(Boolean.TRUE));
		assertThat(cardinality.contains(new Pair("from", 1L)), is(Boolean.TRUE));
	}

	@Test
	public void calculateGOTCardinality() throws ParserConfigurationException,
			SAXException, IOException, Exception {

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		//XPathExpression xPathExpression = xPath.compile("//body//p//text()");
		XPathExpression xPathExpression = xPath.compile("/FictionBook/body/section[1]/section[1]/p/text()");


		File file = new File(
				"/home/dsemenov/dev/projects/fileanalizer/src/test/resources/got/A game of thrones - George Martin.fb2.xml");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();

		Object result = xPathExpression.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < nodes.getLength(); i++) {
			builder.append(nodes.item(i).getNodeValue()).append(" ");
		}

		Set<Pair> cardinality = calculator.extractCardinality(builder
				.toString());
		IndexFile indexFile = IndexFile.makeFile("/home/dsemenov/Downloads/mueller-dict-3.1/dict/mueller-base.index");
        DictionaryFile dictFile = DictionaryFile.makeGZipFile(new File("/home/dsemenov/Downloads/mueller-dict-3.1/dict/mueller-base.dict.dz"));

        DICTDictionary dict = new DICTDictionary.Builder(indexFile, dictFile).enableSplittingIndexFile().build();
		for (Pair pair : cardinality) {
			Definition wordDefinition = dict.lookUp(pair.getWord());
			System.out.println(wordDefinition.getContent());
		}
	}
}
