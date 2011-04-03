from django.db import models

# Create your models here.

class Sehir(models.Model):
	adi = models.CharField(max_length=50)

class Ulke(models.Model):
	adi = models.CharField(max_length=50)

class Konum(models.Model):
	enlem = models.FloatField()
	boylam = models.FloatField()
	ulke = models.ForeignKey(Ulke)
	sehir = models.ForeignKey(Sehir)

class EgitimDurumu(models.Model):
	adi = models.CharField(max_length=50)

class IlgiAlanlari(models.Model):
	adi = models.CharField(max_length=50)

class Meslek(models.Model):
	adi = models.CharField(max_length=50)

class KullaniciDetaylari(models.Model):
	meslek = models.ForeignKey(Meslek)
	egitim_durumu = models.ForeignKey(EgitimDurumu)
	ilgi_alanlari = models.ForeignKey(IlgiAlanlari)

class Kullanici(models.Model):
	ad = models.CharField(max_length=100)
	soyad = models.CharField(max_length=100)
	dogum_tarihi = models.DateTimeField()
	email = models.EmailField()
	parola = models.CharField(max_length=100)
	konum = models.ForeignKey(Konum)
	kullanici_detaylari = models.ForeignKey(KullaniciDetaylari)
