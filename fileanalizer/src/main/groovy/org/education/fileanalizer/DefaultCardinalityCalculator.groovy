package org.education.fileanalizer

import static groovyx.gpars.GParsPool.withPool

import java.util.Map.Entry

class DefaultCardinalityCalculator implements CardinalityCalculator{

	@Override
	public Set<Pair> extractCardinality(String text) {		
		Set<Pair> pairs = new TreeSet<Pair>()
		count(text.split(/\W++/)).each {
			 pairs << new Pair(it.getKey(), it.getValue())
		}
		return pairs
	}
	
	def count(arg) {
		withPool {
		  return arg.parallel
			.map{[it.toLowerCase(), 1]}
			.groupBy{	
				it[0]
			}.getParallel()
			.map {it.value=it.value.size() as Long;it}
			.sort{-it.value}.collection
		}
	  }
}
