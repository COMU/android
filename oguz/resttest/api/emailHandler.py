#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *

import DNS,smtplib
class EmailHandler(BaseHandler):
    allowed_methods=("GET")
    def read(self,request,emailadress):
        emailadress=emailadress.split("@")
        DNS.DiscoverNameServers()
        mxhosts=DNS.mxlookup(emailadress[1])
        if(mxhosts):
            return "dogru"
        else:
            return "yanlis"
