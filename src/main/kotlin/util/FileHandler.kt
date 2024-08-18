package util

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

class FileHandler {
    fun saveImage(file: File, saveLocation: String) {
        val bufferedImage: BufferedImage = ImageIO.read(file)
        val outputStream = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "jpg", outputStream)
        val saveDirectory = File(saveLocation)
        saveDirectory.writeBytes(outputStream.toByteArray())
    }


}