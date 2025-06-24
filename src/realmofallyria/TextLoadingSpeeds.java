
package realmofallyria;

public enum TextLoadingSpeeds {
    
    VERY_SLOW(90),
    SLOW(70),
    NORMAL(50),
    FAST(20),
    VERY_FAST(10);
    
    int textSpeed;
    
    TextLoadingSpeeds(int givenSpeed) {
        
        this.textSpeed = givenSpeed;
        
    }
    
}
