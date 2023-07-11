# iai-resolution-refutation
Java implementation of resolution refutation method with addition of so called "cookbook" as part of Introduction to Artificial Intelligence course at Faculty of Electrical Engineering and Computing, University of Zagreb, academic year 2022./2023.

<br />

**Input arguments:**

- **'--m'** - method (**resolution** or **cooking**)

- **'--f'** - relative path to resolution file

- **'--i'** - relative path to input file (only for **cooking** method)

<br />

**Resolution file:**

- lines starting with **'#'** are comments and are ignored

- clauses are in **CNF** format

- **'v'** - all clauses are defined as disjunction elements (eg. **'a v b'**)

- **'~'** - negation of element (eg. **'~a'**)

- last row is a clause not initially negated to be proven by refutation resolution
 
<br />

**Input file:**

- lines starting with **'#'** are comments and are ignored

- **'-'** - removes clause

- **'+'** - adds clause

- **'?'** - performs query
