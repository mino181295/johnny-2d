package it.unibo.oop.utilities;

import java.util.Objects;

public class Pair<X,Y> {
    
    private X x;
    private Y y;
    
    public Pair(X x, Y y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }
    
    public X getX() {
        return this.x;
    }
    
    public Y getY() {
        return this.y;
    }
    
    public void setX(final X x) {
        this.x = x;
    }
    
    public void setY(final Y y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            final Pair<X, Y> input = (Pair<X, Y>)obj;
            return this.x.equals(input.x) && this.y.equals(input.y)
                   || this.x.equals(input.y) && this.y.equals(input.x);
        }
        return false;
    }
}
