#!/usr/bin/python
# -*- coding: utf-8 -*-

import base64

class DecodeBase64:
    def returnParams(self, encodedParams):
         decodedParams = base64.decodestring(encodedParams)
         list_1 = decodedParams.split('&')
         print list_1
         list_2 = []
         for i in list_1:
             list_2.append(i.split('=')[1])
         return list_2
