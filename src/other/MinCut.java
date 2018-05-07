/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * MinCut.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Jan 14, 2018
 *
 */

package other;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


public class MinCut {

    /**
The file contains the adjacency list representation of a simple undirected graph. There are 200 vertices labeled 1 to 200. The first column in the file represents the vertex label, and the particular row (other entries except the first column) tells all the vertices that the vertex is adjacent to. So for example, the 6th row looks like : "6	155	56	52	120	......". This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with) the vertices with labels 155,56,52,120,......,etc
Your task is to code up and run the randomized contraction algorithm for the min cut problem and use it on the above graph to compute the min cut. (HINT: Note that you'll have to figure out an implementation of edge contractions. Initially, you might want to do this naively, creating a new graph from the old every time there's an edge contraction. But you should also think about more efficient implementations.) (WARNING: As per the video lectures, please make sure to run the algorithm many times with different random seeds, and remember the smallest cut that you ever find.) Write your numeric answer in the space provided. So e.g., if your answer is 5, just type 5 in the space provided.
     */
    private static List<Integer> chooseRandomItems(final HashMap<Integer, ArrayList<Integer>> graph) {
        final ArrayList<Integer> randomItems = new ArrayList<>();

        final int nodeIndex = (int)(Math.random() * graph.keySet().size());
        final Integer randomNode = (Integer)(graph.keySet().toArray()[nodeIndex]);

        final int edgeIndex = (int)(Math.random() * graph.get(randomNode).size());
        final Integer randomEdge = graph.get(randomNode).get(edgeIndex);

        randomItems.add(randomNode);
        randomItems.add(randomEdge);

        return randomItems;
    }

    private static HashMap<Integer, ArrayList<Integer>> copyGraph(final HashMap<Integer, ArrayList<Integer>> graph) {
        final HashMap<Integer, ArrayList<Integer>> graphCopy = new HashMap<>();

        final Iterator it = graph.keySet().iterator();

        while(it.hasNext())
        {
            final Integer currentKey = (Integer)it.next();
            final ArrayList<Integer> currentItemList = graph.get(currentKey);

            graphCopy.put(currentKey, new ArrayList<>(currentItemList));
        }

        return graphCopy;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        final HashMap<Integer, ArrayList<Integer>> originalGraph = readGraphFromFile();

        int minimumCut = 0;

        for(int i = 0; i < 1000; i++) {
            //Copy the original graph in each iteration
            final HashMap<Integer, ArrayList<Integer>> copyGraph = copyGraph(originalGraph);

            final int result = processKargerMinimumCutAlgorithm(copyGraph);

            if(minimumCut == 0){
                minimumCut = result;
            } else {
                if (result < minimumCut) minimumCut = result;
            }

            System.out.println("Partial Result => " + result);
        }

        System.out.println("*** Minimum Cut => " + minimumCut + " ***");
    }


    private static void processKargerAlgorithmStep(final HashMap<Integer, ArrayList<Integer>> graph) {
        final List<Integer> randomItems = chooseRandomItems(graph);

        final Integer firstItem = randomItems.get(0);
        final Integer secondItem = randomItems.get(1);

        final ArrayList<Integer> firstItemList = graph.get(firstItem);
        final ArrayList<Integer> secondItemList = graph.get(secondItem);

        firstItemList.addAll(secondItemList);

        graph.remove(randomItems.get(1));

        final Iterator it = graph.keySet().iterator();

        while (it.hasNext()) {
            final Integer currentKey = (Integer)it.next();

            final ArrayList<Integer> currentItemList = graph.get(currentKey);

            for (final Integer i : currentItemList) {
                if (i.intValue() == secondItem.intValue()) {
                    currentItemList.set(currentItemList.indexOf(i), firstItem);
                }
            }
        }

        //Remove loops
        final ArrayList<Integer> itemsToRemove = new ArrayList<>();

        for (final Integer i : firstItemList) {
            if (i.intValue() == firstItem.intValue()){
                itemsToRemove.add(i);
            }
        }

        firstItemList.removeAll(itemsToRemove);
    }

    /**
     * Process the Karger Minimum Cut Algorithm for a given Graph
     * @param graph The graph to be processed
     * @return The Minimum Cut
     */
    private static int processKargerMinimumCutAlgorithm(final HashMap<Integer, ArrayList<Integer>> graph) {
        //Iterate until there are only two nodes
        while(graph.size() > 2){
            processKargerAlgorithmStep(graph);
        }

        //Return the Minimum Cut (the number of edges of both nodes is the same)
        return graph.get(graph.keySet().toArray()[0]).size();
    }

    /**
     * Reads the Graph used as input for the assignment
     * @return A hash that contains each vertice and the list
     * of vertices linked to it
     */
    private static HashMap<Integer, ArrayList<Integer>> readGraphFromFile() {
        final HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("/temp/kargerMinCut.txt");

            // Get the object of DataInputStream
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                // process the line
                final StringTokenizer tokens = new StringTokenizer(line);
                final ArrayList<Integer> edges = new ArrayList<>();

                // first item is the token
                final Integer node = new Integer(tokens.nextToken());

                while(tokens.hasMoreTokens()) {
                    edges.add(new Integer(tokens.nextToken()));
                }

                graph.put(node, edges);
            }

            br.close();
        } catch (final Exception e) {
        		e.printStackTrace();
        }

        return graph;
    }
}
