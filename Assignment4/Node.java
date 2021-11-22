import java.util.ArrayList;
import java.util.Random;

class Node
{

static final ArrayList<Integer> NEURAL_MAP_VALUES = new ArrayList<Integer>() // provides the framwork for the neural map
    {
        {
            add(4);
            add(3);
            add(2);
        }
    };

    final double max = 1.0;
    final double min = 0.0;

private ArrayList <Node> children; //arraylist that stores the children
private ArrayList <Double> weight; //arraylist that stores the weights
String nodeName; // name for each node. Need to randomize
Random r = new Random();



public Node()
{
    //this.nameOfChildren = new ArrayList<String>();
    this.children = new ArrayList<Node>();
    this.weight = new ArrayList<Double>();
}


    public static void main(String[] args) 
    {
        Node node = new Node();
        node.createChild(0, NEURAL_MAP_VALUES); 
        node.createRandomWeights(0, NEURAL_MAP_VALUES);
        node.printBrain(0);
        
    }
   
    //Recursive method that creates each layer of children
    public void createChild(int currentLayer , ArrayList<Integer> nodesPerLayer)
    {
       //boundry for method
        if (currentLayer >= nodesPerLayer.size())
       
        {
            return;
        }

        
        for(int i = 0;  i < nodesPerLayer.get(currentLayer); i++)
      
        {
            this.children.add(new Node());
        }

         this.children.get(0).createChild(++currentLayer , nodesPerLayer); // having trouble with this line of code

       
       
       for(int i = 1;  i < nodesPerLayer.size(); i++)
       
        {
        
           // this.children.get(i).children = this.children.get(0).children;
            this.children.add(i, this.children.get(0));
        }

    }

    public void createRandomWeights(int currentLayer, ArrayList<Integer> nodesPerLayer)
    {
        
        if(currentLayer >= nodesPerLayer.size())
        
        {
            return;
        }

        
        for(int i = 1; i < nodesPerLayer.get(currentLayer); i++)
        
        {
            double randomValue = min + (max - min) * r.nextDouble();
            this.weight.add(randomValue);
        }

        this.children.get(1).createRandomWeights(++currentLayer, nodesPerLayer);

    }

   
   public void printBrain(int layer)
   
   {
        //indent = '    ' * current_Layer
   
        if(this.children.size() == 0)
   
        {
            System.out.print("\n\t");
            System.out.print(this.nodeName);
            return;
        }
   
   System.out.print("\n\t");
   System.out.print(this.nodeName + " is connected to ");
   
   for(int i = 0; i < this.children.size(); i++) // i = 1?
   
       {
           this.children.get(i).printBrain(layer++);
           if(i < this.weight.size())
           {
             System.out.print("\n\t");
               System.out.print("with weight " + this.weight.get(i));
           }

        } 

  }
}