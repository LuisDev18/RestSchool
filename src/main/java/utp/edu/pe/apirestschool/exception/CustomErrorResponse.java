package utp.edu.pe.apirestschool.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

  private LocalDateTime timestamp;
  private String message;
  private String path;
  private String errorCode;
}
