
public class OnlineBankApp
{
	public static void main(String[] args)
	{

		//create object of BankAccount and pass 1000 as parameter
        BankAccount b = new BankAccount(1000);
        
        
        //create object of withdraw account with parameters
        WithdrawAccount w = new WithdrawAccount(b,10);
        
       // create thread t1 and set name to T1
        Thread wt1 = new Thread(w);
        wt1.setName("T1");

        //CREATE THREAD T2 AND SET NAME TO T2
        Thread wt2 = new Thread(w);
        wt2.setName("T2");

        //start threads
        wt1.start();
        wt2.start();
    }
}	
	
	class BankAccount
	{

    private volatile int balance;

    
    //parameterized constructor
    public BankAccount(int b)
    {
        balance = b;
    }

    //default constructor
    public BankAccount()
    {
        balance = 0;
    }


    //using synchronized
    synchronized public int getBalance(){
        return balance;
    }

    synchronized public int withdraw(int w)
    {
        int b = getBalance();
        //if amount to be withdrawn is less than balance then
        //deduct the amount from balance else return 0
        if(w <= b)
        {
            balance = balance-w;
            return w;
        }
        else
            return 0;
    }
}

class WithdrawAccount implements Runnable{

    private BankAccount acc;
    private int amount;

    //default constructor
    public WithdrawAccount()
    {
        acc = null;
        amount = 0;
    }

    //parameterized constructor
    public WithdrawAccount(BankAccount acc,int amount)
    {
        this.acc = acc;
        this.amount = amount;
    }

    public void run()
    {
        int w; 

        for(int i =0; i<20; i++)
        {
            try 
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //display balance before and after
            System.out.println("Balance before "+Thread.currentThread().getName()+" withdrawl: "+acc.getBalance());
            w = acc.withdraw(amount);
            System.out.println("Balance after "+Thread.currentThread().getName()+" withdrawl: "+acc.getBalance());
           
        }

    }

}

