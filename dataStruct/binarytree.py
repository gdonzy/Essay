# -*- coding: utf-8 -*-
'''
Created on 2016年3月25日

@author: gaoxi
'''
import random
import queue

class BinaryTreeNode(object):
    
    def __init__(self,data,left,right):
        self.data = data
        self.left = left
        self.right = right
        
    def setLeft(self,left):
        self.left = left
    
    def setRight(self,right):
        self.right = right
        
    def setData(self,data):
        self.data = data
    
    def getData(self):
        return self.data
        
class BinaryTree(object):
    
    def __init__(self):
        self.root = None
        
    def addRoot(self,node):
        self.root = node
        
    def makeTree(self,data,left,right):
        self.root = BinaryTreeNode(data,left,right)
        
    def isEmpty(self):
        if self.root == None:
            return True
        else:
            return False
        
    def preOrderRecursive(self,treeNode):
        if treeNode == None:
            return None
        else:
            print(treeNode.data,end=",")
            self.preOrderRecursive(treeNode.left)
            self.preOrderRecursive(treeNode.right)
   
    def inOrderRecursive(self,treeNode):
        if treeNode == None:
            return None
        else:
            self.inOrderRecursive(treeNode.left)
            print(treeNode.data,end=",")
            self.inOrderRecursive(treeNode.right)
    
    def postOrderRecursive(self,treeNode):
        if treeNode == None:
            return None
        else:
            self.postOrderRecursive(treeNode.left)
            self.postOrderRecursive(treeNode.right)
            print(treeNode.data,end=",")
    
    def levelOrderRecursive(self):
        pass
    
    def heightRecursive(self,pNode,h):
        if pNode == None:
            return h
        else:
            height = h + 1
            l_height = self.heightRecursive(pNode.left,height)
            r_height = self.heightRecursive(pNode.right,height)
            height = l_height
            if r_height > l_height:
                height = r_height
            return height
    
    def height(self):
        pass
            

    def preOrder(self):
        stack = []
        pNode = self.root
        while pNode != None :
            while pNode != None :
                print(pNode.data,end=",")
                stack.append(pNode)
                pNode = pNode.left
            
            if len(stack) > 0:
                pNode = stack.pop()
            else:
                pNode = None
            
            while pNode != None and pNode.right == None:
                if len(stack) > 0:
                    pNode = stack.pop()
                else:
                    pNode = None
            
            if pNode != None:
                pNode = pNode.right
                
    def inOrder(self):
        stack = []
        pNode = self.root
        while pNode != None:
            while pNode.left != None:
                stack.append(pNode)
                pNode = pNode.left
            
            print(pNode.data,end=",")
            
            while pNode != None and pNode.right == None:
                if len(stack) > 0:
                    pNode = stack.pop()
                    print(pNode.data,end=",")
                else:
                    pNode = None
            
            if pNode != None :
                pNode = pNode.right

    def postOrder(self):
        stack = []
        pNode = self.root
        markNode = None
        while pNode != None:
            #左子节点压栈
            while pNode != None and pNode.left != markNode: #markNode容易忽略
                stack.append(pNode)
                pNode = pNode.left
            
            if pNode == None:
                pNode = stack.pop()
            
            #没有右子节点的左子节点打印、出栈    
            while pNode != None and pNode.right == None : 
                print(pNode.data,end=",")
                if pNode.left == markNode: #markNode无用
                    markNode = pNode
                if len(stack) > 0:
                    pNode = stack.pop()
                else:
                    pNode = None
            
            #右子树处理    
            if pNode != None and pNode.right != markNode: #markNode可能等于None
                stack.append(pNode)
                pNode = pNode.right
                markNode = pNode
            elif pNode != None and pNode.right == markNode : #markNode等于None的情况容易遗漏
                print(pNode.data,end=",")
                markNode = pNode
                if len(stack) > 0:
                    pNode = stack.pop()
                else:
                    pNode = None
            elif pNode != None and pNode.right == None:
                print(pNode.data,end=",")
                markNode = pNode
                if len(stack) > 0:
                    pNode = stack.pop()
                else:
                    pNode = None         


def addNode(pNode,que):
    randNum = random.randint(1,10)
    if 1 <= randNum <=6:
        way =1
    elif 7 <= randNum <= 8:
        way = 2
    elif 9 <= randNum <=10:
        way = 3
         
    if way == 1:
        if que.empty():
            return pNode
        else:
            left = que.get()
            pNode.setLeft(left)
        
        if que.empty():
            return pNode
        else:
            right = que.get()
            pNode.setRight(right)
            
        addNode(pNode.left,que)
        addNode(pNode.right,que)
        
    if way == 2:
        if que.empty():
            return pNode
        else:
            left = que.get()
            pNode.setLeft(left)
            
        addNode(pNode.left,que)
            
    if way == 3:
        if que.empty():
            return pNode
        else:
            right = que.get()
            pNode.setRight(right)
            
        addNode(pNode.right,que)
        

def mkBinaryTree():
    q = queue.Queue()
    for i in range(0,31):
        q.put(BinaryTreeNode(i,None,None))
    
    tree = BinaryTree()
    pNode = q.get()
    addNode(pNode,q)
    tree.addRoot(pNode)
            
    return tree

if __name__ == '__main__':
    tree = mkBinaryTree()
    print("\nPreOrderRecursive:")
    tree.preOrderRecursive(tree.root)
    print("\nPreOrder:")
    tree.preOrder()
    print("\nInOrderRecursive:")
    tree.inOrderRecursive(tree.root)
    print("\nInOrder:")
    tree.inOrder()
    print("\nPostOrderRecursive:")
    tree.postOrderRecursive(tree.root)
    print("\nPostOrder:")
    tree.postOrder()
    print("\nHeightRecursive")
    print(tree.heightRecursive(tree.root,0))
    
    
        
    
    