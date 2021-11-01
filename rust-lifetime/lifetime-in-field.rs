#[derive(Debug)]
struct Node<'a, T> {
  element: T,
  next: Option<Box<&'a Node<'a, T>>>
}
impl<'a, T> Node<'a, T> {
  fn new(element: T) -> Node<'a, T>{
    Node {
      element,
      next: None
    }
  }
}
fn main() {
  let mut node0 = Node::new(0);
  let mut node1 = Node::new(1);
  let mut node2 = Node::new(2);

  node1.next = Some(Box::new(&node2));
  node0.next = Some(Box::new(&node1));
  println!("{:?}", node0);
  println!("{:?}", node1);
  println!("{:?}", node2);
}