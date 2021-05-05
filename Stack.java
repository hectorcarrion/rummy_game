public class Stack
{
	static Card stack[];
	static int top;
	static int capacity;

	//Creates empty card stack
	Stack(int size)
	{
		stack = new Card[size];
		capacity = size;
		top = -1;
    }

 		//Inserts element into the top of the stack
    public static void Push(Card c){
        if(top == capacity - 1){
            System.out.println("Can't insert to full stack!");

        }
        stack[++top] = c;
    }

		//Removes element from the top of the stack
    public static Card Pop(){
        if(top == -1){
            System.out.println("Can't remove from empty stack!");
        }

        return stack[top--];
    }

		//Returns size of the number of elements in the stack
    public static int Size(){
        return top;
    }

		// hi :)
    public static Boolean isEmpty(){
        if(top == -1){
            System.out.println("Stack is empty.");
            return true;
        }
        return false;
    }

		//Returns the top element of the stack but doesn't remove it
    public static Card Peek(){
        if(top == -1){
            System.out.println("Can't peek from empty stack!");

        }

        return stack[top];
    }

		// Return true if stack is full, false if its not
    public boolean Full(){
        if(top == capacity){
            return true;
        }
        else{
            return false;
        }
     }
}
