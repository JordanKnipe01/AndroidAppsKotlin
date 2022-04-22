package au.edu.usc;

import java.util.*;

class Edge implements Comparable<Edge> {

    Node source;
    Node destination;
    double weight;

    Edge(Node src, Node dest, double wgt) {

        source = src;
        destination = dest;
        weight = wgt;
    }

    // ...
    public String toString() {
        return String.format("(%s -> %s, %f)", source.name, destination.name, weight);
    }


    public int compareTo(Edge otherEdge) {

        if (this.weight > otherEdge.weight) {
            return 1;
        }
        else return -1;
    }

}

class Node {

    int node;
    String name;
    private boolean visited;
    LinkedList<Edge> edges;

    Node(int nde, String name) {
        this.node = nde;
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }
}
class Graph {
    private Set<Node> nodes;
    private boolean directed;

    Graph(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }
    private Node closestUnvisited(HashMap<Node, Double> shortestPathMap) {

        double shortestDistanceBetweenNodes = Double.POSITIVE_INFINITY;
        Node closestReachableNode = null;
        for (Node node : nodes) {
            if (node.isVisited())
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;

            if (currentDistance < shortestDistanceBetweenNodes) {
                shortestDistanceBetweenNodes = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }
    public void Dijkstras(Node start, Node end) {

        HashMap<Node, Node> storageTable = new HashMap<>();
        storageTable.put(start, null);

        // Keeps track of the shortest path
        HashMap<Node, Double> shortestPathMap = new HashMap<>();

        // Setting every node's shortest path weight to positive infinity except starting node = 0
        for (Node node : nodes) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }

        // Iterate through all the nodes reachable from start node
        for (Edge edge : start.edges) {
            shortestPathMap.put(edge.destination, edge.weight);
            storageTable.put(edge.destination, start);
        }

        start.visit();

        // run while loop as long as there is an unvisited node that we can reach
        while (true) {
            Node currentNode = closestUnvisited(shortestPathMap);
             // if node is null means there is no path connected
            if (currentNode == null) {
                System.out.println("There isn't a path between " + start.name + " and " + end.name);
                return;
            }

            // If the closest non-visited node is our destination print path
            if (currentNode == end) {
                System.out.println("The path with the smallest weight between "
                        + start.name + " and " + end.name + " is:");

                Node child = end;
                String path = end.name;
                while (true) {
                    Node parent = storageTable.get(child);
                    if (parent == null) {
                        break;
                    }
                    // print in sequence, begining with parent starting node
                    path = parent.name + " " + path;
                    child = parent;
                }
                System.out.println(path);
                System.out.println("The path costs: " + shortestPathMap.get(end));
                return;
            }
            currentNode.visit();

            // Iterate through all the unvisited nodes our current node has an edge to
            // and check whether its shortest path value is better when going through our
            // current node than the existing pathway we had before
            for (Edge edge : currentNode.edges) {
                if (edge.destination.isVisited())
                    continue;

                if (shortestPathMap.get(currentNode) + edge.weight < shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination,
                            shortestPathMap.get(currentNode) + edge.weight);
                    storageTable.put(edge.destination, currentNode);
                }
            }
        }
    }
    public void addEdge(Node source, Node destination, double weight) {
        //  add the nodes if they don't already exist in our graph
        nodes.add(source);
        nodes.add(destination);

        // addEdgeHelper makes sure we don't have duplicate edges
        addEdgeHelper(source, destination, weight);

        if (!directed && source != destination) {
            addEdgeHelper(destination, source, weight);
        }
    }
    private void addEdgeHelper(Node a, Node b, double weight) {
        // Go through all the edges and see whether that edge has already been added
        for (Edge edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                // Update the value in case it's a different one now
                edge.weight = weight;
                return;
            }
        }
        // If it hasn't been added already, add the edge
        a.edges.add(new Edge(a, b, weight));
    }
}
class IteneraryExplorer {
    public static void main(String[] args) {

        Graph Graph = new Graph(true);


        Node ALBURY = new Node(0, "ALBURY");
        Node ADELAIDE = new Node(1, "ADELAIDE");
        Node ARMIDALE = new Node(2, "ARMIDALE");
        Node ALICE_SPRINGS
                = new Node(3, "ALICE SPRINGS");
        Node AYERS_ROCK
                = new Node(4, "AYERS ROCK");
        Node BUNDABERG = new Node(5, "BUNDABERG");
        Node BROOME = new Node(6, "BROOME");
        Node BRISBANE = new Node(7, "BRISBANE");
        Node CANBERRA
                = new Node(8, "CANBERRA");
        Node COFFS_HARBOUR = new Node(9, "COFFS HARBOUR");
        Node CAIRNS = new Node(10, "CAIRNS");
        Node DUBBO = new Node(11, "DUBBO");
        Node DARWIN = new Node(12, "DARWIN");
        Node GERALDTON = new Node(13, "GERALDTON");
        Node HOBART = new Node(14, "HOBART");
        Node HAMILTON_ISLAND = new Node(15, "HAMILTON ISLAND");
        Node KALGOORLIE = new Node(16, "KALGOORLIE");
        Node KARRATHA= new Node(17, "KARRATHA");
        Node LAUNCESTON= new Node(18, "LAUNCESTON");
        Node SUNSHINE_COAST = new Node(19, "SUNSHINE COAST");
        Node MELBOURNE= new Node(20, "MELBOURNE");
        Node GOLD_COAST = new Node(21, "GOLD COAST");
        Node PERTH= new Node(22, "PERTH");
        Node PROSERPINE= new Node(23, "PROSERPINE");
        Node PORT_MACQUARIE= new Node(24, "PORT MACQUARIE");
        Node BALLINA= new Node(25, "BALLINA");
        Node SYDNEY= new Node(26, "SYDNEY");
        Node PORT_LINCOLN= new Node(27, "PORT LINCOLN");
        Node GLADSTONE = new Node(28, "GLADSTONE");
        Node EMERALD = new Node(29, "EMERALD");
        Node MOUNT_ISA = new Node(30, "MT ISA");
        Node MACKAY = new Node(31, "MACKAY");
        Node MORANBAH = new Node(32, "MORANBAH");
        Node NEWCASTLE = new Node(33, "NEWCASTLE");
        Node ROCKHAMPTON = new Node(34, "ROCKHAMPTON1");
        Node MILDURA = new Node(35, "MILDURA");
        Node PORT_HEDLAND = new Node(36, "PORT HEDLAND");
        Node NEWMAN = new Node(37, "NEWMAN");
        Node TAMWORTH = new Node(38, "TAMWORTH");
        Node WAGGA_WAGGA = new Node(39, "WAGGA WAGGA");
        Node TOWNSVILLE = new Node(40, "TOWNSVILLE");






        // Our addEdge method automatically adds Nodes as well.
        // if we wish to add any
        Graph.addEdge(ALBURY, SYDNEY, 452);

        Graph.addEdge(ADELAIDE, ALICE_SPRINGS, 1316);
        Graph.addEdge(ADELAIDE, BRISBANE, 1622);
        Graph.addEdge(ADELAIDE, CANBERRA, 972);
        Graph.addEdge(ADELAIDE, MELBOURNE, 643);
        Graph.addEdge(ADELAIDE, GOLD_COAST, 1607);
        Graph.addEdge(ADELAIDE, PERTH, 2120);
        Graph.addEdge(ADELAIDE, PORT_LINCOLN, 246);
        Graph.addEdge(ADELAIDE, SYDNEY, 1167);

        Graph.addEdge(ARMIDALE, SYDNEY, 382);

        Graph.addEdge(ALICE_SPRINGS, DARWIN, 1305);
        Graph.addEdge(ALICE_SPRINGS, MELBOURNE, 1860);
        Graph.addEdge(ALICE_SPRINGS, SYDNEY, 2022);

        Graph.addEdge(AYERS_ROCK, SYDNEY, 2181);

        Graph.addEdge(BUNDABERG, BRISBANE, 287);

        Graph.addEdge(BROOME, PERTH, 1677);

        Graph.addEdge(BRISBANE, CANBERRA, 956);
        Graph.addEdge(BRISBANE, CAIRNS, 1391);
        Graph.addEdge(BRISBANE, DARWIN, 2852);
        Graph.addEdge(BRISBANE, EMERALD, 653);
        Graph.addEdge(BRISBANE, GLADSTONE, 434);
        Graph.addEdge(BRISBANE, HOBART, 1791);
        Graph.addEdge(BRISBANE, HAMILTON_ISLAND, 888);
        Graph.addEdge(BRISBANE, MOUNT_ISA, 1573);
        Graph.addEdge(BRISBANE, MELBOURNE, 1381);
        Graph.addEdge(BRISBANE, MACKAY, 797);
        Graph.addEdge(BRISBANE, MORANBAH, 780);
        Graph.addEdge(BRISBANE, NEWCASTLE, 614);
        Graph.addEdge(BRISBANE, PERTH, 3615);
        Graph.addEdge(BRISBANE, PROSERPINE, 895);
        Graph.addEdge(BRISBANE, ROCKHAMPTON, 518);
        Graph.addEdge(BRISBANE, SYDNEY, 753);
        Graph.addEdge(BRISBANE, TOWNSVILLE, 1112);

        Graph.addEdge(CANBERRA, MELBOURNE, 470);
        Graph.addEdge(CANBERRA, SYDNEY, 236);

        Graph.addEdge(COFFS_HARBOUR, SYDNEY, 443);

        Graph.addEdge(CAIRNS, MELBOURNE, 2311);
        Graph.addEdge(CAIRNS, SYDNEY, 1971);
        Graph.addEdge(CAIRNS, TOWNSVILLE, 284);

        Graph.addEdge(DUBBO, SYDNEY, 310);

        Graph.addEdge(DARWIN, MELBOURNE, 3131);
        Graph.addEdge(DARWIN, PERTH, 2651);
        Graph.addEdge(DARWIN, SYDNEY, 3155);

        Graph.addEdge(GERALDTON, PERTH, 370);

        Graph.addEdge(HOBART, MELBOURNE, 618);
        Graph.addEdge(HOBART, SYDNEY, 1039);

        Graph.addEdge(HAMILTON_ISLAND, MELBOURNE, 1965);
        Graph.addEdge(HAMILTON_ISLAND, SYDNEY, 1526);

        Graph.addEdge(KALGOORLIE, PERTH, 538);

        Graph.addEdge(KARRATHA, PERTH, 1250);

        Graph.addEdge(LAUNCESTON, MELBOURNE, 476);
        Graph.addEdge(LAUNCESTON, SYDNEY, 914);

        Graph.addEdge(SUNSHINE_COAST, MELBOURNE, 1454);
        Graph.addEdge(SUNSHINE_COAST, SYDNEY, 837);

        Graph.addEdge(MELBOURNE, MILDURA, 457);
        Graph.addEdge(MELBOURNE, NEWCASTLE, 836);
        Graph.addEdge(MELBOURNE, GOLD_COAST, 1330);
        Graph.addEdge(MELBOURNE, PERTH, 2706);
        Graph.addEdge(MELBOURNE, SYDNEY, 706);
        Graph.addEdge(MELBOURNE, TOWNSVILLE, 2055);

        Graph.addEdge(GOLD_COAST, SYDNEY, 680);

        Graph.addEdge(PERTH, PORT_HEDLAND, 1312);
        Graph.addEdge(PERTH, SYDNEY, 3284);
        Graph.addEdge(PERTH, NEWMAN, 1019);

        Graph.addEdge(PROSERPINE, SYDNEY, 1517);

        Graph.addEdge(PORT_MACQUARIE, SYDNEY, 321);

        Graph.addEdge(BALLINA, SYDNEY, 612);

        Graph.addEdge(SYDNEY, TAMWORTH, 320);
        Graph.addEdge(SYDNEY, TOWNSVILLE, 1690);
        Graph.addEdge(SYDNEY, WAGGA_WAGGA, 367);

        Graph.Dijkstras(BUNDABERG, MELBOURNE);
    }
}