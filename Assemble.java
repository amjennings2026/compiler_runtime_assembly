import java.io.*;
import java.util.*;

public class Assemble
{
    public static String pad(int n, int w)
    {
        int numzeros;
        String nstr=String.valueOf(n);

        numzeros = w - nstr.length();

        String zeros = "";
        while(numzeros>0)
        {
            zeros=zeros+"0";
            numzeros--;
        }
        return (zeros+nstr);
    }


    static void readSrc(String fname) throws IOException {
        // open BufferedReader
        BufferedReader br=new BufferedReader(new FileReader(fname));
        String buffer = null;

        //for each line of text in the assembly source code:
        while((buffer=br.readLine()) != null)
        {
            // skip any blank lines or starting with "#"
            if(buffer.equals("") || buffer.startsWith("#"))
            {
                continue;
            }

            // tokenize string.  1st token is operator, 2nd is operand
            //                              instruction       argument

            StringTokenizer stok=new StringTokenizer(buffer);
            String operator=stok.nextToken();
            String operand=stok.nextToken();

            // If operand is invalid, display error and abort.
            int ioperand=Integer.parseInt(operand);
            if(ioperand>99)
            {
                System.out.print("NOT A VALID ARGUMENT!!!!");
                return;
            }


            // convert string operator to numeric form.
            String operators[]={"HALT","ADD","SUB","MLT","DIV","ILOAD","LOAD","S
            int opcode=-1;

            for(int i=0;i<operators.length;i++)
            {
                if(operator.equals(operators[i]))
                {
                    opcode=i;
                    continue;
                }
            }


            // If operator is invalid, display error and abort.
            if(opcode==-1)
            {
                System.out.print("NOT A VALID ARGUMENT!!!!");
                return;
            }

            // output opcode and operand, each padded to two characters.
            System.out.println(pad(opcode,2)+pad(ioperand,2));

        }
    }

    public static void main(String args[]) throws IOException
    {
        if (args.length != 1)
        {
            System.out.print("usage:  java Assemble INPUTFILE");
            System.exit(0);
        }

        readSrc(args[0]);      //intakes STRING
    }
}
