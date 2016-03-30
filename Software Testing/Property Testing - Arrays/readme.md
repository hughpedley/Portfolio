CS1632 Deliverable 4
Spring Semester 2015

DUE 24 MAR 2016

Deliverable 4

For this assignment, you will: Write your own JUnit-based property-based test to check Arrays.sort(int[] arr) method in Java

There are no partners for this deliverable.

Generate a minimum of 100 different random arrays of different sizes, and test different properties (many examples were discussed in the lecture on property-based testing) of sorting them. You may use Java's built-in Arrays.sort() method. You should test at least three different properties of each sorted array. You should use traditional JUnit test procedures (e.g., use assertions, don't use System.out.println during normal execution, etc.) Since we are testing a built-in Java method, I don't expect any failures, but who knows, you may be the one to find a bug in Java's own libraries!

You may do this either all in one JUnit test method, or with one method per property.

I expect an approximately one-page (3 or 4 paragraphs) description of why you chose this project, how you went about doing it, any issues you faced, and what you learned. If you are doing the combinatorial testing option, use this space to explain what you are testing. If it is existing software, please provide a link; if it is software that you have thought of yourself, please explain it in enough detail that I can understand what you are testing.

Both of these options also will require a screenshot. Please put this on its own page. For the combinatorial testing project, I expect a screenshot of NIST ACTS or whatever other tool you are using, specifically showing the all-pairs covering array. For the property-based testing project, I expect a screenshot of the executed JUnit test(s).
