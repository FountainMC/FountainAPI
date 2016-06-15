# Contributing to FountainAPI
This is a comprehensive guide to contributing to FountainAPI.
It will cover getting the code, editing the code, and submitting your changes.

## Before we get started
If you're on Linux, you can skip this.

Windows users need to [install MinGW](https://sourceforge.net/projects/mingw/files/Installer/).
There are many guides out on the internet on how to, so we won't be covering that here.

OSX users need to install [Homebrew](http://brew.sh/) and install Git.

```
$ brew install git
```


The majority of the code you'll see here are commands that you need to execute in a terminal.
Window's Command Prompt _will not work, no matter how hard you try_.

## Getting the code
First we need to clone the repository. I recommend you do this over SSH.

```
$ git clone git@github.com:FountainMC/FountainAPI.git
```

Doing it over HTTPS is also an option.

```
$ git clone https://github.com/FountainMC/FountainAPI
```

## Editing the code
Here's the hard part: editing the code. We don't want you to submit ugly, unformatted code, so
we made this guide to help you out.

### Style Guide

> If you are using Eclipse or IntelliJ to develop, you can import the formatters located in extra/
> These will not adhere to all the guidelines! Be sure to read below to see what _you_ need to do!

We follow [Google's Java Style](https://google.github.io/styleguide/javaguide.html) with some changes.


* Line endings

  * Use Unix line endings when committing (\\n)

    * Windows users of Git can do ``git config --global core.autocrlf true`` to let Git convert them automatically

* Column width

  * 80 for Javadocs
  * 150 for code
  * Feel free to wrap when it will help with readability.

* Indentation

  * Use 4 spaces for indentations, do not use 2 spaces.

* Vertical whitespace

  * Place a blank line before the first member of a class, interface, enum, etc., as
    well as after the last member.
    
    ```
    public interface Example {
    
        void method();
    
    }
    ```

* Imports

  * Imports must be grouped in the following order, where each group is separated by an empty line.

    * ``java`` imports
    * ``javax`` imports
    * All other imports
    * Static imports

  * This differs from Google's style in that imports are not grouped by top-level package, they are all grouped as one.

* Exceptions

  * For exceptions that are to be ignored, name the exception variable ``ignored``.

* Field accesses

  * Qualify **all** field accesses with ``this``

* Javadocs

  * Do not use ``@author``
  * Wrap additional paragraphs in ``<p>`` and ``</p>``
  * Capitalize the first letter in the descriptions within each "at clause", i.e. ``@param name Player to affect``, no
    periods.

### Comitting your changes
This is a very important setp in submitting changes. If you're commits are trash, we won't accept them.

First, we need to add our changed files. The most common way of doing this is `git add -A`, but I like to only add the folders which I've changed.

```
$ git add src
```

Next, we need to commit them.

```
$ git commit -v
# The verbose flag shows the changes you've made in your default text editor.
```

#### Commit messages are important
Commit messages need to follow these guidelines.

* Separate subject from body with a blank line
* Limit the subject line to 50 characters
* Capitalize the subject line
* Do not end the subject line with a period
* Use the imperative mood in the subject line
* Wrap the body at 72 characters
* Use the body to explain what and why vs. how

### Pushing your changes
Be sure to [fork FountainAPI](https://github.com/FountainMC/FountainAPI/) on GitHub.

We want to push our changes to our fork, so we'll use the fork's url.

```
$ git remote add fork https://github.com/<username>/FountainAPI
$ git push fork master
```

Now you can open a Pull Request!