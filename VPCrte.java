import java.io.*;

public class VPCrte
{
    static final int MEMSIZ = 100;

    static final int HALT  = 0;
    static final int ADD   = 1;
    static final int SUB   = 2;
    static final int MLT   = 3;
    static final int DIV   = 4;
    static final int ILOAD = 5;
    static final int LOAD  = 6;
    static final int STOR  = 7;
    static final int READ  = 8;
    static final int WRITE = 9;
    static final int BR    = 10;
    static final int BZ    = 11;
    static final int BN    = 12;
    static final int DUMP  = 13;

    static int MEMORY[] = new int[MEMSIZ];

    static int PCREG;
    static int IRREG;
    static int GPREG;

    static boolean debug = false;

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
    public static void readToMemory(String fname) throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader(fname));
        String buffer = null;
        int count=0;

        // for each line of "machine code":
        while((buffer=br.readLine()) != null)
        {
            if(buffer.equals("") || buffer.startsWith("#"))
            {
                continue;
            }

            // display info about code as it is loaded into memory
            System.out.println("readToMemory: MEMORY ["+pad(count,2)+"] = ("+buf
            int ibuffer=Integer.parseInt(buffer);

            MEMORY[count]=ibuffer;
            count++;

            if (count>=MEMSIZ)
            {
            {
                System.out.print("Memory Overflow");
                break;
            }
        }
        br.close();


    }


    public static void dumpMemory()
    {
        System.out.println("====================================================
        int c=0;

        System.out.println("PCREG = " + pad(PCREG, 4));
        System.out.println("IRREG = " + pad(IRREG, 4));
        System.out.println("GPREG = " + pad(GPREG, 4) + "\n");

        System.out.println("MEMORY:     0     1     2     3     4     5     6
        System.out.println("    ------------------------------------------------

        for (int i=0; i<MEMSIZ; i++)
        {
            if ((i%10) == 0)
            {
                System.out.print("     " + c + "|");
                c++;
            }

            System.out.print("  " + pad(MEMORY[i], 4));

            if (((i+1)%10) == 0)
                System.out.println();
        }
        System.out.println();
        System.out.println("====================================================
    }

    public static void runProg() throws IOException
    {
        String opcodes[]={"HALT","ADD","SUB","MLT","DIV","ILOAD","LOAD","STOR","

        PCREG = 0;

        while(PCREG>=0)
        {
            // fetch current instruction from memory and copy to IRREG
            IRREG=MEMORY[PCREG];

            // extract opcode and operand from IRREG
            int opcode=IRREG/100;
            int operand=IRREG%100;

            if (debug)
            {
                System.out.print("readToMemory: MEMORY["+pad(opcode,2)+"] = "+pa
                System.out.print("opcode = "+pad(opcode,2)+", ");
                System.out.print("operand = "+pad(operand,2)+", ");
                System.out.print("GPREG = "+pad(GPREG,4));
                System.out.print("("+opcodes[opcode]+")");
                System.out.println();
            }

            // handle all of the opcodes with a large if/else if/else if/...
            // or switch statement.
            if(opcode==HALT)
            {
                break;
            }
            else if(opcode==ADD)
            {
                GPREG=GPREG+MEMORY[operand];
            }
            else if(opcode==SUB)
            {
                GPREG=GPREG-MEMORY[operand];
            }
            else if(opcode==MLT)
            {
                GPREG=GPREG*MEMORY[operand];
            }
            else if(opcode==DIV)
            {
                GPREG=GPREG/MEMORY[operand];
            }
            else if(opcode==ILOAD)
            {
                GPREG=operand;
            }
            else if(opcode==LOAD)
            {
                GPREG=MEMORY[operand];
            }
            else if(opcode==STOR)
            {
                MEMORY[operand]=GPREG;
            }
            else if(opcode==READ)
            {
                System.out.print("["+operand+"]");
                BufferedReader input=new BufferedReader(new InputStreamReader(Sy
                String  str=input.readLine();
                int num=Integer.parseInt(str);
                MEMORY[operand]=num;
            }
            else if(opcode==WRITE)
            {
                System.out.println("["+operand+"] -> "+MEMORY[operand]);
            }
            else if(opcode==BR)
            {
                PCREG=operand-1;
                continue;
            }
            else if(opcode==BZ)
            {
                if(GPREG==0)
                {
                    PCREG=operand-1;
                    continue;
                }
            }
            else if(opcode==BN)
            {
                if(GPREG!=0)
                {
                    PCREG=operand-1;
                    continue;
                }
            }
            else if(opcode==DUMP)
            {
                System.out.println("======================================");
                System.out.println("["+operand+"] -> "+MEMORY[PCREG]);
                System.out.println("======================================");
            }
            PCREG++;

        }
    }

    public static void main(String args[]) throws IOException
    {
        if (args.length == 0)
        {
            System.out.println("usage: java VPCrte FILENAME.exe [ debug ]");
            System.exit(0);
        }

        if ((args.length == 2) && (args[1].equals("debug"))) debug = true;

        readToMemory(args[0]);

        if (debug) dumpMemory();

        runProg();
    }
}
