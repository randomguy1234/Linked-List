/* The point of this class is to introduce 3 new methods that
   can be useful for lists. This class was written by Michael Brown*/
public class List3 
{ //beginning of class

	public static void main(String[] args) 
	{
		List<Integer> listA= new LList<Integer>();
		List<Integer> listB= new LList<Integer>();
		List<Integer> listC= new LList<Integer>();
		List<Integer> listD= new LList<Integer>();

		listA.append(2);
		listA.append(3);
		listA.append(6);
		listA.append(8);
		
		
		
		listB.append(1);
		listB.append(3);
		listB.append(5);
		listB.append(9);

		
		listC.append(2);
		listC.append(4);
		listC.append(6);
		listC.append(83);
		
		
		listD.append(2);
		listD.append(5);
		listD.append(7);
		listD.append(9);
		
		//interchange test(normal conditions + special cases)
		System.out.println("The original listA is the following: "+listA);
		interchange(listA);
		System.out.println("The list after the interchange is the following: "+listA);
		
		//reverse test(normal conditions + special case)
		System.out.println("The original listB is the following: "+listB);
		reverse(listB);
		System.out.println("The list in reverse order is the following: "+listB);
		
		//merge test(normal conditions + several special cases)
		System.out.println("The original listC and listD are the following: "+listC+ " and "+ listD);
		System.out.println("The merged list is the following: "+merge(listC,listD));
		
	}

	
	//interchange method is supposed to swap the current element of the list with the next element
	public static void interchange(List<Integer> L)
	{//beginning of interchange method 
		
		if (L.length()==0)
			System.out.println("There are no items in the list!");
		
		else if(L.length()>=1 && L.currPos()<L.length()-1)
		//move to element after curr element,store value, move to prev element and then insert value in front of it
		
		{
			L.next();  
			int temp= L.getValue(); 
			L.prev();
			L.insert(temp);
			
			//move curr after the 2 elements and remove the unwanted copy
			L.next();
			L.next();
			L.remove();
		}
		
		else
			
			if(L.currPos()<L.length()) //curr is pointing at the last element in the list
				System.out.println("There is no other element to interchange with in the list.");
			
			else //curr is pointing at nothing
				System.out.println("There are no more elements in the list.");
	}//end of interchange method
	
	
	//reverse method is supposed to reverse the order of elements in the list
	public static void reverse(List<Integer> L)
	{//beginning of reverse method
		
		
		List<Integer> result= new LList<Integer>();
		
		for(L.moveToStart();L.currPos()<L.length();L.next())
			result.insert(L.getValue());	//the reverse order of the elements is stored here
		
		L.clear();//empties the original list
		
		for(result.moveToStart();result.currPos()<result.length();result.next())
			L.append(result.getValue());	//the elements in result is copied back into the original list
	
	}//end of reverse method
	
	
	//merge method is supposed to return a new list which is the combination of 2 input lists 
	//the 2 inputs are assumed to be sorted and the new list is returned sorted  
	public static List<Integer> merge(List<Integer> L1, List<Integer> L2)
	{//beginning of merge method
		
		List<Integer> answer= new LList<Integer>();
		
		if(L1.length()==0 && L2.length()==0) 
		{
			System.out.println("Both lists were empty!");
			return answer;
		}
	
		
		for(L1.moveToStart(),L2.moveToStart();L1.currPos()!=L1.length() || L2.currPos()!=L2.length();)
		
			/*if statement checks for 3 situations:
			 * a)1st list is bigger than 2nd list
			 * b)2nd list is bigger than 1st list
			 * c)both list are of the same size    */
			
			
				if(L1.length()>L2.length())
				{
					//special case: last/remaining element(s) value in 1st list > last element value in 2nd list 
					if(L2.currPos()<L2.length()) 
				
						//special case: Last element value in 1st list < last element value in 2nd list
						if (L1.getValue()!=null && L1.getValue()<=L2.getValue())
						{
							answer.append(L1.getValue());
							L1.next();
						}
					
						else
						{
							answer.append(L2.getValue());
							L2.next();
						}
					
					else //leftover element(s) from 1st list are added to answer
					{
						answer.append(L1.getValue());
						L1.next();
					}
					
				}
			
				
				else if(L1.length()==L2.length())
				{
					//special case: either list has an element that is not in the merged list
					if(L2.getValue()!=null && L1.getValue()!=null)
						if(L1.getValue()<=L2.getValue()) //Assumption:element from 1st list value is smaller 
						{
							answer.append(L1.getValue());
							L1.next();
						}
					
						else
						{
							answer.append(L2.getValue());
							L2.next();
						}
					
					else
					{	
						if(L2.currPos()==L2.length()) //no more elements in 2nd list to compare with
						{
							answer.append(L1.getValue());
							L1.next();
						}
					
						else // no more elements in the 1st list to compare with
						{
							answer.append(L2.getValue());
							L2.next();
						}
					}
				}
				
	
				else
				{
					//special case: last/remaining element(s) value in 2nd list > last element value in 1st list
					if(L1.currPos()<L1.length())
						
						//special case: last element value in 2nd list < last element value in 1st list
						if(L2.getValue()!=null && L2.getValue()<=L1.getValue())
						{
							answer.append(L2.getValue());
							L2.next();
						}
					
						else
						{
							answer.append(L1.getValue());
							L1.next();
						}
					
					else //leftover element(s) from 2nd list are added to answer
					{
						answer.append(L2.getValue());
						L2.next();
					}
				}
			
			
		return answer;
		
	}//end of merge method
	
} //end of class
