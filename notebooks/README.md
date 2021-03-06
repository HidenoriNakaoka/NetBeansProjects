This project contains example code for working with
[ImageJ](https://imagej.net/ImageJ) and [SciJava](https://imagej.net/SciJava).

WHY JUPYTER NOTEBOOKS
---------------------
[Jupyter Notebooks](https://jupyter.org/) are a great outreach tool to demonstrate how programs work interactively in real time. We use Jupyter for teaching ImageJ2 so that you can try and change the code yourself easily. See the [Intro to Jupyter notebook](Intro-to-Jupyter.ipynb) if you have never used Jupyter notebook before.

RUNNING JUPYTER NOTEBOOKS
-------------------------

There are multiple ways to [run the Jupyter Notebooks](https://jupyter.org/install):

| Method | Pros | Cons | Running Live? |
| --- | --- | --- | --- |
| [Local <BR> Environment](#recommended-local-environment) | Run code locally on your machine. | Must install developer tools. | Yes |
| [On GitHub](#on-github) | View notebooks quickly on GitHub's website. | Code does not run live; rendering is less <BR>complete & correct than on nbviewer. | No |
| [Binder](#binder) |	Run code on the cloud, no local installation. | 	Slow to spin up the web container. | Yes | 		
| [Nbviewer](#nbviewer) | View notebooks nicely rendered on nbviewer.org. | Code does not run live;<BR> not integrated with GitHub. | No |

### (Recommended) Local Environment
1. Install [Miniconda](https://conda.io/miniconda.html).
2. Clone this `imagej/tutorials` repository.
3. Open a console and `cd` to your cloned working copy.
4. `conda env create -f environment.yml` to create a conda environment with the
   dependencies these notebooks need.
5. `conda activate scijava` to activate the environment.
6. `jupyter notebook` to launch Jupyter Notebook in a web browser window.
7. In the browser, click into `notebooks`, then click on the
   `ImageJ-Tutorials-and-Demo.ipynb` notebook to open it.

### On GitHub
The easiest way to get started with the ImageJ and SciJava APIs is via the
ImageJ Jupyter notebooks, located in the `notebooks` subfolder of this repository.

### Binder
[![Binder](https://mybinder.org/badge.svg)](https://mybinder.org/v2/gh/imagej/tutorials/master)

Use the "launch binder" badge above to try the Jupyter notebooks on the cloud
using [Binder](https://mybinder.org), with no local installation necessary.

Note: Binder startup may take a minute

### Nbviewer
[![Nbviewer](https://camo.githubusercontent.com/a2b8b49ec63c501c07f7f5d73ced6fdee58a337609d4a6962d6ec5b4fbd3fec9/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f72656e6465722d6e627669657765722d6f72616e67652e737667)](https://nbviewer.org/github/imagej/tutorials/tree/master/)

Use the "render nbviewer" badge above to access the Jupyter Notebooks on your machine through nbviewer.

## Notebook Technologies
The introductory notebooks use the Groovy kernel from
[BeakerX](http://beakerx.com). Several other JVM-based kernels
are usable as well, including Clojure, Java, Kotlin and Scala.

There are also notebooks using the standard Python kernel plus
the [pyimagej](https://pypi.org/project/pyimagej) package,
enabling use of ImageJ from Python programs.

LICENSING
---------

To the extent possible under law, the ImageJ developers have waived
all copyright and related or neighboring rights to this tutorial code.

See [unlicense.org](https://unlicense.org/) for details.


SEE ALSO
--------

* The [Tutorials](https://imagej.net/tutorials) and [Development](https://imagej.net/develop) sections of the ImageJ wiki.
