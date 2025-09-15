import java.io.IOException;

public class FiniteStateMachineProceduralExplicit 
{
    public enum State 
    {
        A(true),
        B(false),
        E(false);

        public boolean isValidEndState;

        State(boolean isValidEndState)
        {
            this.isValidEndState = isValidEndState;
        }
    }

    public State currentState = State.A;
    public State transitionsTable[][] = 
        {
            {State.B, State.E},
            {State.A, State.B},
            {State.E, State.E}
        };

    public void processInputSymbol(char inputSymbol)
    {
        int rowIndex = currentState.ordinal();
        int columnIndex;
        
        switch (inputSymbol) 
        {
            case 'a':
                columnIndex = 0;
                currentState = transitionsTable[rowIndex][columnIndex];
                break;
            case 'b':
                columnIndex = 1;
                currentState = transitionsTable[rowIndex][columnIndex];
                break;
            default:
                currentState = State.E;
                break;
        }
    }

    public static void main(String[] args)
    {
        try 
        {
            FiniteStateMachineProceduralExplicit finiteStateMachine = new FiniteStateMachineProceduralExplicit();
            char inputSymbol;

            while ((inputSymbol = (char)System.in.read()) != '\r')
            {
                finiteStateMachine.processInputSymbol(inputSymbol);
            }
            
            if (finiteStateMachine.currentState.isValidEndState)
            {
                System.out.println("Input is a VALID sequence.");
            }
            else
            {
                System.out.println("Input is an INVALID sequence.");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}