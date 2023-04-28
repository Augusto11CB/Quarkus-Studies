TLD="local"
OU="my-org-unit"
O="my-org"
L="my-city"
S="my-state"
C="AU"
PASSWORD="password"
openssl req -new -x509 -keyout fake-ca-1.key \
	-out fake-ca-1.crt -days 9999 \
	-subj "/CN=ca1.${TLD}/OU=${OU}/O=${O}/L=${L}/S=${S}/C=${C}" \
	-passin pass:$PASSWORD -passout pass:$PASSWORD
