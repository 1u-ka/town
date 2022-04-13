#!make

default:
	#
manifests:
	lh build src/kube/api.edn \
		src/kube/ingress.edn \
		src/kube/service.edn
