package s4.B171811; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/* What is imported from s4.specification
   package s4.specification;
   public interface InformationEstimatorInterface{
   void setTarget(byte target[]); // set the data for computing the information quantities
   void setSpace(byte space[]); // set data for sample space to computer probability
   double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
   }                        
   */

public class InformationEstimator implements InformationEstimatorInterface{
  // Code to tet, *warning: This code condtains intentional problem*
  byte [] myTarget; // data to compute its information quantity
  byte [] mySpace;  // Sample space to compute the probability
  FrequencerInterface myFrequencer;  // Object for counting subByteFrequency
  private double [] iqMem = null; // array of information quantity

  byte [] subBytes(byte [] x, int start, int end) {
    // corresponding to substring of String for  byte[] ,
    // It is not implement in class library because internal structure of byte[] requires copy.
    byte [] result = new byte[end - start];
    for(int i = 0; i<end - start; i++) { result[i] = x[start + i]; };
    return result;
  }

  private void initIqMem() {
    if(this.iqMem == null) this.iqMem = new double[this.myTarget.length];
    for(int i = 0; i < this.myTarget.length; i++) {
      this.iqMem[i] = f(myFrequencer.subByteFrequency(0, i+1));
      for(int k = 1; k < i - 1; k++) {
        var tmp = this.iqMem[k] + f(myFrequencer.subByteFrequency(k, i+1));
        if(this.iqMem[i] > tmp) this.iqMem[i] = tmp;
      }
    }
  }

  // public int COUNT = 0;
  // IQ: information quantity for a count,  -log2(count/sizeof(space))
  private double f(int freq) {
    // COUNT++;
    return  - Math.log10((double) freq / (double) mySpace.length)/ Math.log10((double) 2.0);
  }

  public void setTarget(byte [] target) { 
    myTarget = target;
    this.iqMem = null;
  }
  public void setSpace(byte []space) { 
    myFrequencer = new Frequencer();
    mySpace = space;
    myFrequencer.setSpace(space); 
  }

  public double estimation(){
    this.myFrequencer.setTarget(this.myTarget);
    initIqMem();
    return this.iqMem[this.myTarget.length - 1];
  }

  public static void main(String[] args) {
    InformationEstimator myObject;
    double value;
    myObject = new InformationEstimator();
    myObject.setSpace("3210321001230123".getBytes());
    myObject.setTarget("0".getBytes());
    value = myObject.estimation();
    System.out.println(">0 "+value);
    myObject.setTarget("01".getBytes());
    value = myObject.estimation();
    System.out.println(">01 "+value);
    myObject.setTarget("0123".getBytes());
    value = myObject.estimation();
    System.out.println(">0123 "+value);
    myObject.setTarget("00".getBytes());
    value = myObject.estimation();
    System.out.println(">00 "+value);

    myObject = new InformationEstimator();
    myObject.setSpace("123456789123456789123456789123456789123456789123456789123456789".getBytes());
    myObject.setTarget("123456789".getBytes());
    value = myObject.estimation();
    System.out.println(">123456789 "+value);

    // System.out.println("count: " + myObject.COUNT);
  }
}
