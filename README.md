# Inventory App Stage 1

Project #8 for Android Basics by Google Nanodegree Program

<h3>Layout</h3>
<table>
  <tr>
    <th>Criteria</th>
    <th>Meets Specifications</th>
  </tr>
  <tbody>
    <tr>
      <td>
        <p>Overall Layout</p>
      </td>
      <td>
        <p>No UI is required for this project.</p>
        <p>Hint: At minimum, you will need a main activity that has methods to read data, a Contract Java class, and a DbHelper Java class.</p>
        <p>Note: Even though UI is not required for this Stage, we highly recommend that you test your insert/read methods with log calls. Often, students do not realize their code has SQL syntax errors until the app is run and the methods are called which results in the project not passing.</p>
      </td>
    </tr>
  </tbody>
</table>

<h3>Functionality</h3>
<table>
  <tr>
    <th>Criteria</th>
    <th>Meets Specifications</th>
  </tr>
  <tbody>
    <tr>
      <td>
        <p>Compile Time Errors</p>
      </td>
      <td>
        <p>The code compiles without errors.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Table Definition</p>
      </td>
      <td>
        <p>There exists a contract class that defines name of table and constants.</p>
        <p>Inside the contract class, there is an inner class for each table created.</p>
        <p>The contract contains at minimum constants for the Product Name, Price, Quantity, Supplier Name, and Supplier Phone Number.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Table Creation</p>
      </td>
      <td>
        <p>There exists a subclass of SQLiteOpenHelper that overrides onCreate() and onUpgrade().</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Data Insertion</p>
      </td>
      <td>
        <p>There is a single insert method that adds:</p>
        <ul>
          <li>Product Name</li>
          <li>Price</li>
          <li>Quantity</li>
          <li>Supplier Name</li>
          <li>Supplier Phone Number</li>
        </ul>
        <p>It is up to you to decide what datatype (e.g. INTEGER, STRING) each of these values should be; however, it is required that there are at least 2 different datatypes (e.g. INTEGER, STRING).</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Data Reading</p>
      </td>
      <td>
        <p>There is a single method that uses a Cursor from the database to perform a query on the table to retrieve at least one column of data. Also the method should close the Cursor after it's done reading from it.</p>
      </td>
    </tr>
  </tbody>
</table>

<h3>Code Readability</h3>
<table>
  <tr>
    <th>Criteria</th>
    <th>Meets Specifications</th>
  </tr>
  <tbody>
    <tr>
      <td>
        <p>Readability</p>
      </td>
      <td>
        <p>Code is easily readable such that a fellow programmer can understand the purpose of the app.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Naming Conventions</p>
      </td>
      <td>
        <p>All variables, methods, and resource IDs are descriptively named such that another developer reading the code can easily understand their function.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Format</p>
      </td>
      <td>
        <p>The code is properly formatted:</p>
        <ul>
          <li>No unnecessary blank lines</li>
          <li>No unused variables or methods</li>
          <li>No commented out code</li>
        </ul>
        <p>The code also has proper indentation when defining variables and methods.</p>
      </td>
    </tr>
  </tbody>
</table>

<em>Requirements copied from: <a href="http://udacity.com">udacity.com</a>.</em>

<br />
<p align="center"> 
  <img src="showcase/inventory_app_stage_1.jpg" alt="Project Image 1">
</p>


