terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "4.6.0"
    }
  }
}

provider "docker" {
  host = "unix:///var/run/docker.sock"
}

resource "docker_image" "nginx" {
  name         = "nginx:alpine"
  keep_locally = true
}

resource "docker_container" "nginx" {
  name  = "terraform-nginx-lab"
  image = docker_image.nginx.image_id

  ports {
    ip       = "127.0.0.1"
    internal = 80
    external = 8083
  }
}
