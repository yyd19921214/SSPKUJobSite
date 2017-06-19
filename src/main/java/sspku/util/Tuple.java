package sspku.util;

public class Tuple<L,R> {

	  private final L left;
	  private final R right;

	  public Tuple(L left, R right) {
	    this.left = left;
	    this.right = right;
	  }

	  public L getLeft() { return left; }
	  public R getRight() { return right; }

	  @Override
	  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (!(o instanceof Tuple)) return false;
	    @SuppressWarnings("unchecked")
		Tuple<L, R> pairo = (Tuple<L, R>) o;
	    return this.left.equals(pairo.getLeft()) &&
	           this.right.equals(pairo.getRight());
	  }

}

