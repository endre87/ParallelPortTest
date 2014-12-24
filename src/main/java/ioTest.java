//package jnpout32;
import hardware.jnpout32.*;
//import java.lang.Integer;

public class ioTest
{
          static short datum;
          static short Addr;
	    static pPort lpt;

    static void do_read()
    {
          // Read from the port
          datum = (short) lpt.input(Addr);

          // Notify the console
          System.out.println("Read Port: " + Integer.toHexString(Addr) +
                              " = " +  Integer.toHexString(datum));
     }

     static void do_write()
     {
          // Notify the console
          System.out.println("Write to Port: " + Integer.toHexString(Addr) +
                              " with data = " +  Integer.toHexString(datum));
          //Write to the port
          lpt.output(Addr,datum);
     }


     static void do_read_range()
     {
          // Try to read 0x378..0x37F, LPT1:
          for (Addr=0x378; (Addr<0x380); Addr++) {

               //Read from the port
               datum = (short) lpt.input(Addr);

               // Notify the console
               System.out.println("Port: " + Integer.toHexString(Addr) +
                                   " = " +  Integer.toHexString(datum));
          }
     }


     public static void main( String args[] ) throws InterruptedException
     {

	    lpt = new pPort();
 
          // Try to read 0x378..0x37F, LPT1:

           do_read_range();


     //  Write the data register

     Addr = 0x378;

        for (int i = 0 ;i <20; i++) {
            datum = (short) (i%2);
            do_write();
            do_read();
            Thread.sleep(500);
        }

    }


}
