all: Dockerfile	
	docker images 2>&1 | grep -qe tds-h2 || docker image build -t tds-h2 .

run:
	sudo docker run -it \
		 -p 0.0.0.0:1099:1099 tds-h2

run-2:
	docker run  --rm -it --name my-running-tds tds-h2
