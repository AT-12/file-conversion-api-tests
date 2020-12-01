mkdir -p $HOME/docker
mv /tmp/docker-compose.yml $HOME/docker/docker-compose.yml
cd $HOME/docker
docker-compose up -d