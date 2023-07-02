package br.com.gerenciadorcontatos.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource; //classe que ajuda a pegar mensagens de erro, de acordo com o idioma da requisicao
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) //fala que deve retornar 400, se nao tivesse feito isso, o spring iria entender que tratamos o erro, e iria retornar 200
	@ExceptionHandler(MethodArgumentNotValidException.class) //diz para o spring que o metodo abaixo deve ser chamado quando houver alguma exeção dentro de algum controller
	public List<ErroValidation> handle(MethodArgumentNotValidException exception) {
		List<ErroValidation> dto = new ArrayList<ErroValidation>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroValidation erro = new ErroValidation(e.getField(), mensagem);
			dto.add(erro);
		});
		return dto;
	}
	
	/* esta classe funciona como uma espécie de interceptador, 
	 * sempre que tiver um erro, o spring virá nela para ser tratadp */
	
}