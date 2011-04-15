import random
import base64

def generate_url_id(leng):
   nbits = leng * 6 + 1
   bits = random.getrandbits(nbits)
   uc = u"%0x" % bits
   newlen = int(len(uc) / 2) * 2
   ba = bytearray.fromhex(uc[:newlen])
   return base64.urlsafe_b64encode(str(ba))[:leng]


if __name__ == "__main__":
    print generate_url_id(30)
