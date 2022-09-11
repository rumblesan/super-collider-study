# Explanations

Explaining some of the SuperCollider concepts so I remember them.

## Classes

[Writing Classes](http://doc.sccode.org/Guides/WritingClasses.html)

## Environments and ProxySpace

`~varname` is a shortcut to create a global variable called `varname` in the current environment.
The `p = ProxySpace.push(s);` in *Setup.scd* creates a new ProxySpace that becomes the current environment.

[Environment](https://doc.sccode.org/Classes/Environment.html)
[ProxySpace](http://doc.sccode.org/Classes/ProxySpace.html)

```
~abc = 10;

currentEnvironment; // Displays "Environment[ (abc -> 10) ]"
```
