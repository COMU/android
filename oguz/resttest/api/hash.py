#!/usr/bin/python
# -*- coding: utf-8 -*-

import hashlib

class Crypt:
    def returnHash(self,password):
        m = hashlib.md5()
        m.update(password)
        return m.hexdigest()
