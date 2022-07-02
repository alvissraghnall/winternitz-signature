

#  Winternitz Signatures


Various implementations of the Winternitz Hash-based signature scheme — originally Typescript, Java & Python.

*The Winternitz Signature Scheme involves three parts:*

 - Key Generation: A Cryptographically secure random Number generator (CSPRNG) is used in generating 32 32-byte numbers. And this is the private key.
Each of these 32 numbers are then hashed 256 times(using SHA-256) to generate the Public Key.
 
 
 -  Signature Generation: The message is hashed using the SHA-256 algorithm, and the Binary representation of this hash is considered. We split the 256-bit hash value in 32 eighth's and calculate the decimal equivalent of each eighth. We subtract this decimal equivalent from 256, and the resulting value represents how much we hash the current eighth of binary digits. The signature is obtained after this recursively hashing.
 
 

 -  Signature Verification: The Signature Verification function (or method) is provided with three arguments — the message, the signature, and the public key. 
 Firstly, we hash the message, using the SHA-256 Hashing Algorithm, generating a digest of 32 8-bit values.
 

