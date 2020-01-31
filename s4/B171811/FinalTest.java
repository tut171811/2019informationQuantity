package s4.B171811;
import s4.specification.*;

class FinalTest {
  public static void main(String args[]) {
    int c;
    c = 0;
    try {
      FrequencerInterface myObject;
      int freq;
      c = 0;
      System.out.println("checking Frequencer");
      myObject = new Frequencer();
      freq = myObject.frequency();
      if(-1 != freq) { System.out.println("frequency() should return -1, when target is not set, but returns"+freq); c++; }
      myObject = new Frequencer();
      myObject.setTarget("".getBytes());
      freq = myObject.frequency();
      if(-1 != freq) { System.out.println("frequency() should return -1, when target is empty, but return"+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAA".getBytes());
      if(-1 != freq) { System.out.println("frequency() for AAA should return -1, when target is not set. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAA".getBytes());
      myObject.setTarget("".getBytes());
      freq = myObject.frequency();
      if(-1 != freq) { System.out.println("frequency() for AAA should return -1, when taget empty string. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setTarget("A".getBytes());
      freq = myObject.frequency();
      if(0 != freq) { System.out.println("frequency() for not set, should return 0, when taget is not empty. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("".getBytes());
      myObject.setTarget("A".getBytes());
      freq = myObject.frequency();
      if(0 != freq) { System.out.println("frequency() for empty space, should return 0, when taget is not empty. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAA".getBytes());
      myObject.setTarget("A".getBytes());
      freq = myObject.frequency();
      if(3 != freq) { System.out.println("frequency() for AAA, should return 3, when taget is A. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAA".getBytes());
      myObject.setTarget("AA".getBytes());
      freq = myObject.frequency();
      if(2 != freq) { System.out.println("frequency() for AAA, should return 2, when taget is AA. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAA".getBytes());
      myObject.setTarget("AAA".getBytes());
      freq = myObject.frequency();
      if(1 != freq) { System.out.println("frequency() for AAA, should return 1, when taget is AAA. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAA".getBytes());
      myObject.setTarget("AAAA".getBytes());
      freq = myObject.frequency();
      if(0 != freq) { System.out.println("frequency() for AAA, should return 0, when taget is AAAA. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("H".getBytes());
      freq = myObject.frequency();
      if(4 != freq) {System.out.println("frequency() for Hi_Ho_Hi_Ho, should return 4, when taget is H. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("Ho".getBytes());
      freq = myObject.frequency();
      if(2 != freq) {System.out.println("frequency() for Hi_Ho_Hi_Ho, should return 2, when taget is Ho. But it returns "+freq); c++; }
      /* please note subByteFreqency(0,0) is considered illeagal specification, and you should not include
       * this case */
      myObject = new Frequencer();
      myObject.setSpace("AAAB".getBytes());
      myObject.setTarget("AAAAB".getBytes());
      freq = myObject.subByteFrequency(0,1);
      if(3 != freq) { System.out.println("SubBytefrequency() for AAAB, should return 3, when taget is AAAAB[0:1]. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAAB".getBytes());
      myObject.setTarget("AAAAB".getBytes());
      freq = myObject.subByteFrequency(1,2);
      if(3 != freq) { System.out.println("SubBytefrequency() for AAAB, should return 2, when taget is AAAAB[1:2]. But it returns "+freq); c++; }
      if(2 == freq) { System.out.println("You might be confused by the intentional error in sample code."); }
      myObject = new Frequencer();
      myObject.setSpace("AAAB".getBytes());
      myObject.setTarget("AAAAB".getBytes());
      freq = myObject.subByteFrequency(1,3);
      if(2 != freq) { System.out.println("SubBytefrequency() for AAAB, should return 2, when taget is AAAAB[1:3]. But it returns "+freq); c++; }
      myObject = new Frequencer();
      myObject.setSpace("AAAB".getBytes());
      myObject.setTarget("AAAAB".getBytes());
      freq = myObject.subByteFrequency(4,5);
      if(1 != freq) {
        System.out.println("SubBytefrequency() for AAAB, should return 1, when taget is AAAAB[4:5]. But it returns "+freq); c++;
      }
    }
    catch(Exception e) {
      System.out.println("Exception occurred in Frequencer Object: STOP");
      c++;
    }
    try {
      InformationEstimatorInterface myObject;
      double value;
      System.out.println("checking InformationEstimator");
      myObject = new InformationEstimator();
      myObject.setSpace("3210321001230123".getBytes());
      myObject.setTarget("0".getBytes());
      value = myObject.estimation();
      if((value < 1.9999) || (2.0001 <value)) { System.out.println("IQ for 0 in 3210321001230123 should be 2.0. But it returns "+value); c++; }
      myObject.setTarget("01".getBytes());
      value = myObject.estimation();
      if((value < 2.9999) || (3.0001 <value)) { System.out.println("IQ for 01 in 3210321001230123 should be 3.0. But it returns "+value); c++; }
      myObject.setTarget("0123".getBytes());
      value = myObject.estimation();
      if((value < 2.9999) || (3.0001 <value)) { System.out.println("IQ for 0123 in 3210321001230123 should be 3.0. But it returns "+value); c++; }
      myObject.setTarget("00".getBytes());
      value = myObject.estimation();
      if((value < 3.9999) || (4.0001 <value)) { System.out.println("IQ for 00 in 3210321001230123 should be 4.0. But it returns "+value); c++; }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
      c++;
    }
    if(c == 0) { System.out.println("TestCase OK"); }
  }
}
