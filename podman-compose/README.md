### [Podman Compose with Podman Desktop](https://podman-desktop.io/docs/compose/podman-compose)

1. Install [podman](https://podman.io/) and [podman desktop](https://podman-desktop.io/docs/compose/podman-compose).
2. Install [podman-compose](https://github.com/containers/podman-compose#installation).
For example on Ubuntu it can be installed with the following commands:
~~~
$ sudo apt install python3-pip -y
$ pip3 install podman-compose
~~~

Add the following to your `~/.bashrc`
~~~
if [ -d "$HOME/.local/bin" ] ; then
  PATH="$PATH:$HOME/.local/bin"
fi
~~~

3. Build and start the compose file:
~~~
$ podman-compose -f podman-compose.yaml build && podman-compose -f podman-compose.yaml up -d
~~~
