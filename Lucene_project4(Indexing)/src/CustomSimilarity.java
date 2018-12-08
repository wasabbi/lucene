import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.util.BytesRef;

public class CustomSimilarity extends DefaultSimilarity{

	@Override
	public float coord(int overlap, int maxOverlap) {
		// TODO Auto-generated method stub
		if(overlap != maxOverlap)
			return 0;	//overlap / maxOverlap
		else
			return 1;
	}

	@Override
	public float lengthNorm(FieldInvertState arg0) {
		// TODO Auto-generated method stub
		return 1;  //FieldInvertState.getLength() if setDiscountOverlaps(boolean) is false, 
										//else it's FieldInvertState.getLength() - FieldInvertState.getNumOverlap().
	}

	@Override
	public float queryNorm(float sumOfSquaredWeights) {
		// TODO Auto-generated method stub
		return 1;	// 1/sqrt(sumOfSquaredWeight)
	}

}

