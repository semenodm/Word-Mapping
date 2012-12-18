package org.education.fileanalizer;

import java.util.Set;

public interface CardinalityCalculator {

	Set<Pair> extractCardinality(String text);

}
