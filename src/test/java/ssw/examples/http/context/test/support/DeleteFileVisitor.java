/**
 * 
 */
package ssw.examples.http.context.test.support;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author m.genova
 *
 * @param T T extends java.nio.file.Path
 */
public class DeleteFileVisitor<T extends Path> implements FileVisitor<T> {

	@Override
	public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs)
			throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(T file, BasicFileAttributes attrs)
			throws IOException {
		Files.delete(file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(T file, IOException exc)
			throws IOException {
		return FileVisitResult.SKIP_SUBTREE;
	}

	@Override
	public FileVisitResult postVisitDirectory(T dir, IOException exc)
			throws IOException {
		Files.delete(dir);
		return FileVisitResult.CONTINUE;
	}


}
