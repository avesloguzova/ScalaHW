

trait GraphBase {
  graph =>
  type Node <: NodeBase
  type Edge <: EdgeBase

  trait WithOut {
    val out: graph.type = graph
  }

  trait NodeBase extends WithOut {
    this: Node =>
    def connect(other: out.Node): out.Edge =
      newEdge(this, other)
  }

  trait EdgeBase extends WithOut {
    this: Edge =>
    val from: out.Node
    val to: out.Node
  }

  val nodes: scala.collection.mutable.Set[Node] = new scala.collection.mutable.HashSet[Node]()
  val edges: scala.collection.mutable.Set[Edge] = new scala.collection.mutable.HashSet[Edge]()

  def newNode(): Node

  def newEdge(from: Node, to: Node): Edge

  def addNode(node: Node): Unit = {
    nodes += node
  }

  def addEdge(edge: Edge): Unit = {
    edges += edge
  }
}

class DirectedGraph extends GraphBase {

  class Node extends NodeBase

  class Edge(val from: Node, val to: Node) extends EdgeBase

  override def newNode() = new Node

  override def newEdge(from: Node, to: Node) = new Edge(from, to)


}

class UndirectedGraph extends DirectedGraph {
  override def addEdge(edge: Edge): Unit = {
    edges += edge
    edges += newEdge(edge.to, edge.from)
  }
}

class ColouredGraph extends GraphBase {

  class Node(val colour: String) extends NodeBase

  class Edge(val from: Node, val to: Node) extends EdgeBase

  def newNode() = new Node("white")

  def newEdge(from: Node, to: Node) = new Edge(from, to)
}

trait Graph {
  val graph: GraphBase

  def nodes = graph.nodes

  def edges = graph.edges

  def addNode() = {
    val node = graph.newNode()
    graph.addNode(node)
    node
  }

  def connect(from: graph.Node, to: graph.Node) = {
    val edge = from.connect(to)
    graph.addEdge(edge)
    edge
  }
}
