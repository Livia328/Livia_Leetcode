import easyocr
from PIL import Image

image_path = 'img1.jpg'
image = Image.open(image_path)
rgb_image = image.convert('RGB')
rgb_image.save('temp_image.jpg')


reader = easyocr.Reader(['ch_sim','en']) 
result = reader.readtext('temp_image.jpg')
for i in result:
    word = i[1]
    print(word)