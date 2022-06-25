package test1;

import java.io.Serializable;

public class sudukoSolvedRiddle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long duration;
    private int[][] riddle;
    private int[][] matrix;
    
    public Long getDuration() {
		return duration;
	}
    
    
    public void setDuration(Long duration) {
		this.duration = duration;
	}
    
   public int[][] getMatrix() {
	return matrix;
   }
   
   public int[][] getRiddle() {
	return riddle;
   }
   
   public void setMatrix(int[][] matrix) {
	this.matrix = matrix;
   }
   
   public void setRiddle(int[][] riddle) {
	this.riddle = riddle;
   }

}
