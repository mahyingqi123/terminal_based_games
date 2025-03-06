""" AVL Tree implemented on top of the standard BST. """

__author__ = 'Alexey Ignatiev'
__docformat__ = 'reStructuredText'

from bst import BinarySearchTree
from typing import TypeVar, Generic
from node import AVLTreeNode

K = TypeVar('K')
I = TypeVar('I')


class AVLTree(BinarySearchTree, Generic[K, I]):
    """ Self-balancing binary search tree using rebalancing by sub-tree
        rotations of Adelson-Velsky and Landis (AVL).
    """

    def __init__(self) -> None:
        """
            Initialises an empty Binary Search Tree
            :complexity: O(1)
        """

        BinarySearchTree.__init__(self)
        self.k = None

    def get_height(self, current: AVLTreeNode) -> int:
        """
            Get the height of a node. Return current.height if current is 
            not None. Otherwise, return 0.
            :complexity: O(1)
        """

        if current is not None:
            return current.height
        return 0

    def get_balance(self, current: AVLTreeNode) -> int:
        """
            Compute the balance factor for the current sub-tree as the value
            (right.height - left.height). If current is None, return 0.
            :complexity: O(1)
        """

        if current is None:
            return 0
        return self.get_height(current.right) - self.get_height(current.left)

    def get_right_node_count(self, current: AVLTreeNode) -> int:
        """
        author: Mah Ying Qi
        To get the node count in the right subtree
        :param current: a tree node
        :return: Return the node count in the right subtree
        """
        if current is None:
            return 0
        return current.right_node_count

    def insert_aux(self, current: AVLTreeNode, key: K, item: I) -> AVLTreeNode:
        """
        author: Mah Ying Qi
            Attempts to insert an item into the tree, it uses the Key to insert
            it. After insertion, performs sub-tree rotation whenever it becomes
            unbalanced.
            returns the new root of the subtree.
            :precondition: the key should not be in the tree already
            :complexity Best = O(1), when current is None
                        Worst = O(log(N)), N = number of nodes in the tree
            :post condition: key and item are inserted at the correct position with the tree balanced
            :invariant: current subtree is balanced
        """
        if current is None:  # base case: at the leaf
            current = AVLTreeNode(key, item)
            self.length += 1
        elif key < current.key:
            current.left = self.insert_aux(current.left, key, item)
        elif key > current.key:
            current.right = self.insert_aux(current.right, key, item)
            current.right_node_count += 1  # One node added to the right subtree
        else:  # key == current.key
            raise ValueError('Inserting duplicate item')
        current = self.rebalance(current)  # Rebalance after inserting
        current.height = 1 + max(self.get_height(current.right), self.get_height(current.left))
        # Update height after inserting
        return current

    def delete_aux(self, current: AVLTreeNode, key: K) -> AVLTreeNode:
        """
        author: Mah Ying Qi
            Attempts to delete an item from the tree, it uses the Key to
            determine the node to delete. After deletion,
            performs sub-tree rotation whenever it becomes unbalanced.
            returns the new root of the subtree.
            :complexity: The key should be in the tree
            :complexity Best = O(1), when current is None
                        Worst = O(log(N)), N = number of nodes in the tree
            :post condition: key and item are deleted from the tree with the tree balanced
            :invariant: current subtree is balanced
        """
        if current is None:  # key not found
            raise ValueError('Deleting non-existent item')
        elif key < current.key:
            current.left = self.delete_aux(current.left, key)
        elif key > current.key:
            current.right = self.delete_aux(current.right, key)
            current.right_node_count -= 1
        else:  # we found our key => do actual deletion
            if self.is_leaf(current):
                self.length -= 1
                return None
            elif current.left is None:
                self.length -= 1
                return current.right
            elif current.right is None:
                self.length -= 1
                return current.left
            # general case => find a successor
            succ = self.get_successor(current)
            current.key = succ.key
            current.item = succ.item
            current.right = self.delete_aux(current.right, succ.key)
            current.right_node_count -= 1  # One node deleted in the current subtree
        current = self.rebalance(current)  # rebalance after deleting
        current.height = 1 + max(self.get_height(current.right), self.get_height(current.left))
        # update height after deleting
        return current

    def left_rotate(self, current: AVLTreeNode) -> AVLTreeNode:
        """
        author: Mah Ying Qi
            Perform left rotation of the sub-tree.
            Right child of the current node, i.e. of the root of the target
            sub-tree, should become the new root of the sub-tree.
            returns the new root of the subtree.
            Example:

                 current                                       child
                /       \                                      /   \
            l-tree     child           -------->        current     r-tree
                      /     \                           /     \
                 center     r-tree                 l-tree     center
            :precondition: current should not be a leaf
            :return The new root of the subtree
            :post condition: root changed, tree rotated left
            :complexity: O(1)
        """
        child = current.right  # get the right subtree of current as child
        current.right = child.left  # Make the right link of the current becomes child's left subtree
        child.left = current  # Make current as left subtree of child
        current.right_node_count -= (child.right_node_count+1)  # decrease right node count of current
        current.height = 1 + max(self.get_height(current.right), self.get_height(current.left))  # update current's height
        child.height = 1 + max(self.get_height(child.right), self.get_height(child.left))  # update child's height
        return child

    def right_rotate(self, current: AVLTreeNode) -> AVLTreeNode:
        """
        author: Mah Ying Qi
            Perform right rotation of the sub-tree.
            Left child of the current node, i.e. of the root of the target
            sub-tree, should become the new root of the sub-tree.
            returns the new root of the subtree.
            Example:

                       current                                child
                      /       \                              /     \
                  child       r-tree     --------->     l-tree     current
                 /     \                                           /     \
            l-tree     center                                 center     r-tree

            :complexity: O(1)
            :precondition: current should not be a leaf
            :post condition: root changed, tree rotated right
            :return The new root of the subtree
        """
        child = current.left  # get the left subtree of current as child
        current.left = child.right  # Make the left link of the current becomes child's right subtree
        child.right = current  # Make current as right subtree of child
        child.right_node_count += (1 + current.right_node_count)  # increase the right node count of child
        current.height = 1 + max(self.get_height(current.right), self.get_height(current.left))  # update current's height
        child.height = 1 + max(self.get_height(child.right), self.get_height(child.left))  # update child's height
        return child

    def rebalance(self, current: AVLTreeNode) -> AVLTreeNode:
        """ Compute the balance of the current node.
            Do rebalancing of the sub-tree of this node if necessary.
            Rebalancing should be done either by:
            - one left rotate
            - one right rotate
            - a combination of left + right rotate
            - a combination of right + left rotate
            returns the new root of the subtree.
        """
        if self.get_balance(current) >= 2:
            child = current.right
            if self.get_height(child.left) > self.get_height(child.right):
                current.right = self.right_rotate(child)
            return self.left_rotate(current)

        if self.get_balance(current) <= -2:
            child = current.left
            if self.get_height(child.right) > self.get_height(child.left):
                current.left = self.left_rotate(child)
            return self.right_rotate(current)

        return current

    def kth_largest(self, k: int) -> AVLTreeNode:
        """
        author: Mah Ying Qi
        Returns the kth largest element in the tree.
        k=1 would return the largest.
        Check the right subtree if there are more than k nodes in it, if true search the right half.
        If false, search the left subtree and minus k by node count in right subtree + 1
        If the number of nodes in right subtree is equal to k - 1,
        current is the node we are searching for, because there are k - 1 elements bigger than current node,
        meaning kth largest is the current node.
        hence return current
        :param k: to find the node that is kth largest
        :precondition: k should not be greater than the number of nodes in the tree
        :complexity:    N = number of Nodes
                        Best = O(1), When current is the kth largest node in the tree
                        Worst = O(log(N)), The algorithm will only traverse down one branch
                        of the tree, as any branch of the tree will have height <= log(N),
                        the worst case will be the algorithm traverse log(N) time.
        :post condition: tree not modified, kth largest node found
        :return: the kth largest node in the tree/subtree
        """
        return self.kth_largest_aux(self.root, k)  # call the aux function

    def kth_largest_aux(self, current: AVLTreeNode,k: int) -> AVLTreeNode:
        """
        author: Mah Ying Qi
        Returns the kth largest element in the tree.
        k=1 would return the largest.

        Check the right subtree if there are more than k nodes in it, if true search the right half.
        If false, search the left subtree and minus k by node count in right subtree + 1
        If the number of nodes in right subtree is equal to k - 1,
        current is the node we are searching for, because there are k - 1 elements bigger than current node,
        meaning kth largest is the current node.
        hence return current

        :param current: the node to start searching from
        :param k: to find the node that is kth largest
        :precondition: k should not be greater than the number of nodes in the tree
        :complexity:    N = number of Nodes
                        Best = O(1), When current is the kth largest node in the tree
                        Worst = O(log(N)), The algorithm will only traverse down one branch
                        of the tree, as any branch of the tree will have height <= log(N),
                        the worst case will be the algorithm traverse log(N) time.
        :post condition: tree not modified, kth largest node found
        :invariant: kth largest item is current node or in the next subtree
        :return: the kth largest node in the tree/subtree
        """
        if current is None:
            return None
        if self.get_right_node_count(current) == k - 1:
            # node count in right subtree is k-1, hence current node is kth largest
            return current
        elif self.get_right_node_count(current) > k - 1:
            # kth largest is in right subtree, as right node count is more than k - 1
            return self.kth_largest_aux(current.right, k)
        else:
            # kth largest is in left subtree, as right node count is less than k - 1
            return self.kth_largest_aux(current.left, k-self.get_right_node_count(current)-1)






