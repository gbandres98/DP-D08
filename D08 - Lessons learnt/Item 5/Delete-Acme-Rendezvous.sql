start transaction;
use `acmerendezvous`;
revoke all privileges on `acmerendezvous`.* from 'acme-user'@'%';
revoke all privileges on `acmerendezvous`.* from 'acme-manager'@'%';
drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';
drop database `acmerendezvous`;
commit;